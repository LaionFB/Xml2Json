package br.edu.cefsa.ec6.laion.xml;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.edu.cefsa.ec6.laion.datastructure.Stack;

public class Xml {
	private static Pattern tokenPattern = Pattern.compile("<(\\/?\\w[\\w\\d-_]*)>", Pattern.MULTILINE);
	private XmlNode xmlRootNode = null;
	
	public static Xml parse(String text){
		return new Xml(text);
	}
	
	private Xml(String xmlText){
		Matcher tokenMatcher = tokenPattern.matcher(xmlText);
		Stack<OpenedTag> openedTags = new Stack<OpenedTag>();
		
		while(tokenMatcher.find()){
			if(this.xmlRootNode != null)
				throw new XmlParseException("Xml has more than one root tag.");
			
			String tagName = tokenMatcher.group(1);
			
			if(isOpenTagToken(tagName)){
				openedTags.put(new OpenedTag(tagName, tokenMatcher.start(), tokenMatcher.end()));
				continue;
			}

			validateClosingTag(openedTags, tagName.substring(1), tokenMatcher.start());
			
			OpenedTag tagToClose = openedTags.pop();
			
			XmlNode newTag;
			if(tagToClose.getChildren().isEmpty())
				newTag = new XmlNodeString(tagToClose.getName(), xmlText.substring(tagToClose.getContentStartIndex(), tokenMatcher.start()));
			else
				newTag = newXmlNodeObject(tagToClose);

			if(!openedTags.isEmpty()){
				openedTags.see().putChild(newTag);
			}else{
				this.xmlRootNode = newTag;
			}		
		}
		
		if(!openedTags.isEmpty()){
			OpenedTag notClosedTag = openedTags.pop();
			String format = "Tag '%s' was opened and never closed (at position %s).";
			throw new XmlParseException(String.format(format, notClosedTag.getName(), notClosedTag.getTagIndex()));
		}
		else if(this.xmlRootNode == null){
			throw new XmlParseException("There are no xml tags in this text.");
		}
	}
	
	public String toJson(){
		return this.xmlRootNode.toJson(new String(), false);
	}
	
	private static boolean isOpenTagToken(String token){
		return token.indexOf('/') == -1;
	}
	
	private static void validateClosingTag(Stack<OpenedTag> openedTags, String closingTagName, int position){
		if(openedTags.isEmpty()){
			String format = "Cannot close tag '%s' since it has never been opened (at position %s).";
			throw new XmlParseException(String.format(format, closingTagName, position));
		}
		if(!openedTags.see().getName().equals(closingTagName)){
			OpenedTag openedTag = openedTags.pop();
			String format = "Tag '%s' cannot be closed since tag '%s' is the current opened tag (at position %s).";
			throw new XmlParseException(String.format(format, closingTagName, openedTag.getName(), position));			
		}
	}
	
	private static XmlNodeObject newXmlNodeObject(OpenedTag openedTag){
		try{
			return new XmlNodeHashMap(openedTag.getName(), openedTag.getChildren());
		} catch(XmlParseException e){ }
		try{
			return new XmlNodeArray(openedTag.getName(), openedTag.getChildren());
		} catch(XmlParseException e){ }
		
		String format = "Tag '%s' is neighter an array nor a dictionary, " + 
				"since it's children have neighter all the same key " +
				"nor all different keys (at position %s).";
		throw new XmlParseException(String.format(format, openedTag.getName(), openedTag.getTagIndex()));
	}
}
