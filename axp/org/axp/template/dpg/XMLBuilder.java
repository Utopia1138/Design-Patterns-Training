package org.axp.template.dpg;

import java.util.Stack;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLBuilder {
	protected final Document doc;
	protected final Stack<Element> stack;
	
	public XMLBuilder() {
		try {
			this.doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			this.stack = new Stack<Element>();
		}
		catch( ParserConfigurationException e ) {
			throw new IllegalStateException( e );
		}
	}
	
	public Document getDocument() {
		while ( !this.stack.isEmpty() ) {
			finish();
		}
		
		return this.doc;
	}
	
	public XMLBuilder start( String element, String ... attrPairs ) {
		this.stack.push( this.doc.createElement( element ) );
		setAttributes( this.stack.peek(), attrPairs );
		
		if ( this.stack.size() == 1 ) {
			this.doc.appendChild( this.stack.peek() );
		}
		
		return this;
	}
	
	public XMLBuilder finish() {
		if ( this.stack.isEmpty() ) {
			throw new IllegalStateException( "Can't finish element; none open" );
		}
		
		Element e = this.stack.pop();
		
		if ( !this.stack.isEmpty() ) {
			this.stack.peek().appendChild( e );
		}
		
		return this;
	}
	
	public XMLBuilder addChild( String element, String value, String ... attrPairs ) {
		Element elem = this.doc.createElement( element );
		setAttributes( elem, attrPairs );
		elem.appendChild( this.doc.createTextNode( value ) );
		this.stack.peek().appendChild( elem );

		return this;
	}
	
	protected static void setAttributes( Element elem, String[] attrPairs ) {
		if ( attrPairs.length % 2 == 1 ) {
			throw new IllegalArgumentException( "Number of attributes doesn't match number of values" );
		}
		
		for ( int i = 0; i < attrPairs.length; i += 2 ) {
			elem.setAttribute( attrPairs[i], attrPairs[i + 1] );
		}		
	}
}
