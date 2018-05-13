package br.edu.cefsa.ec6.laion.xml;

import java.util.List;
import java.util.stream.Collectors;

final class XmlNodeHashMap extends XmlNodeObject {
	public XmlNodeHashMap(String name, List<XmlNode> children){
		super(name, children, '{', '}', true);

		List<String> childrenNames = children.stream().map(tag -> tag.getName()).collect(Collectors.toList());
		for(int i = 1; i < childrenNames.size(); i++){
			for(String tagName : childrenNames.subList(0, i)){
				if(tagName.equals(childrenNames.get(i))){
					String format = "Tag '%s' is not a hash map since it's children has repeated names.";
					throw new XmlParseException(String.format(format, name));
				}
			}
		}
	}
}
