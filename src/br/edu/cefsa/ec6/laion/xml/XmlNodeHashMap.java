package br.edu.cefsa.ec6.laion.xml;

import java.util.List;
import java.util.stream.Stream;

final class XmlNodeHashMap extends XmlNodeObject {
	
	public XmlNodeHashMap(String name, List<XmlNode> children){
		super(name, children, '{', '}', true);

		Stream<String> childrenNames = this.children.stream().map(tag -> tag.getName()).distinct();
		if(childrenNames.count() < this.children.size()){
			String format = "Tag '%s' is not a hash map since it's children has repeated names.";
			throw new XmlParseException(String.format(format, name));
		}
	}
	
}
