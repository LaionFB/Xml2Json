package br.edu.cefsa.ec6.laion.xml;

import java.util.List;
import java.util.stream.Stream;

final class XmlNodeArray extends XmlNodeObject {
	
	public XmlNodeArray(String name, List<XmlNode> children){
		super(name, children, '[', ']', false);

		Stream<String> childrenNames = this.children.stream().map(tag -> tag.getName()).distinct();
		if(childrenNames.count() > 1){
			String format = "Tag '%s' is not an array since it's children has different names.";
			throw new XmlParseException(String.format(format, name));
		}
	}
	
}
