package org.axp.template.dpg;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

public class XMLQuery {
	protected static XPath path = XPathFactory.newInstance().newXPath();
	
	public static String get( String expression, Document document ) {
		try {
			return XMLQuery.path.compile( expression ).evaluate( document );
		}
		catch ( XPathExpressionException e ) {
			return null;
		}
	}
}
