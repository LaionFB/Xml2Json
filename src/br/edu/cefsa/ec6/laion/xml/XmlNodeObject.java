package br.edu.cefsa.ec6.laion.xml;

import java.util.ArrayList;
import java.util.List;

abstract class XmlNodeObject extends XmlNode {
	protected List<XmlNode> children;
	private char startDelimiter;
	private char endDelimiter;
	private boolean childDisplaysName;
	
	protected XmlNodeObject(String name,
							List<XmlNode> children,
							char startDelimiter,
							char endDelimiter,
							boolean childDisplaysName){
		super(name);
		this.children = new ArrayList<XmlNode>(children);
		this.startDelimiter = startDelimiter;
		this.endDelimiter = endDelimiter;
		this.childDisplaysName = childDisplaysName;
	}
	
	@Override
	public String toJson(String padding, boolean displayName){
		StringBuilder sb = new StringBuilder();
		sb.append(padding);
		
		if(displayName)
			sb.append(this.getFormatedName());
		
		sb.append(startDelimiter);
		sb.append(System.lineSeparator());
		
		for(int i = 0; i < this.children.size(); i++){
			sb.append(this.children.get(i).toJson(padding + '\t', childDisplaysName));
			if(i < this.children.size() - 1){
				sb.append(',');
			}
			sb.append(System.lineSeparator());
		}
		sb.append(padding);
		sb.append(endDelimiter);
		return sb.toString();
	}
}
