package br.edu.cefsa.ec6.laion.xml;

final class XmlNodeString extends XmlNode {
	private String content;
	
	public XmlNodeString(String name, String content){
		super(name);
		this.content = content;
	}
	
	@Override
	public String toJson(String padding, boolean displayName){
		StringBuilder sb = new StringBuilder();
		sb.append(padding);
		if(displayName)
			sb.append(this.getFormatedName());
		sb.append(String.format("\"%s\"", this.content));
		return sb.toString();
	}
}
