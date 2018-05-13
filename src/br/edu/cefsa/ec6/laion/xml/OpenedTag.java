package br.edu.cefsa.ec6.laion.xml;

import java.util.ArrayList;
import java.util.List;

class OpenedTag {
	private String name;
	private int contentStartIndex;
	private int tagIndex;
	private List<XmlNode> childrenTags;
	
	public OpenedTag(String name, int tagIndex, int contentStartIndex) {
		this.tagIndex = tagIndex;
		this.name = name;
		this.contentStartIndex = contentStartIndex;
		this.childrenTags = new ArrayList<XmlNode>(); 
	}

	public String getName() {
		return this.name;
	}
	public int getContentStartIndex() {
		return this.contentStartIndex;
	}
	public int getTagIndex(){
		return this.tagIndex;
	}
	public void putChild(XmlNode child){
		this.childrenTags.add(child);
	}
	public List<XmlNode> getChildren(){
		return this.childrenTags;
	}
}
