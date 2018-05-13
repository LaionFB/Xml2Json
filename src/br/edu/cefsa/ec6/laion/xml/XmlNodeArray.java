package br.edu.cefsa.ec6.laion.xml;

import java.util.List;
import java.util.stream.Collectors;

final class XmlNodeArray extends XmlNodeObject {
	
	public XmlNodeArray(String name, List<XmlNode> children){
		super(name, children, '[', ']', false);
		
		List<String> childrenNames = this.children.stream().map(tag -> tag.getName()).collect(Collectors.toList());
		for(int i = 1; i < childrenNames.size(); i++){
			for(String tagName : childrenNames.subList(0, i)){
				if(!tagName.equals(childrenNames.get(i))){
					String format = "Tag '%s' is not an array since it's children has different names.";
					throw new XmlParseException(String.format(format, name));
				}
			}
		}
	}
	
}
