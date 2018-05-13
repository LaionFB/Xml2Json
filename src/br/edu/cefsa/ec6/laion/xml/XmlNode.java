package br.edu.cefsa.ec6.laion.xml;

abstract class XmlNode {
	protected String name;
	
	protected XmlNode(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public abstract String toJson(String paddingStart, boolean displayName);
	
	protected String getFormatedName(){
		return String.format("\"%s\": ", this.name);
	}
}
