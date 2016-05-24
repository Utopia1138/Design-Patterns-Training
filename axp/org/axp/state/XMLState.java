package org.axp.state;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public abstract class XMLState extends DefaultHandler {
	protected final GameEventController controller;
	
	public XMLState( GameEventController controller ) {
		this.controller = controller;
	}
	
	protected static String getAttr( String elementName, String attr, Attributes attributes ) {
		if ( attributes == null || attributes.getValue( attr ) == null ) {
			throw new IllegalArgumentException( elementName + " without " + attr + '!' );
		}
		
		return attributes.getValue( attr );
	}
	
	protected static String getId( String elementName, Attributes attributes ) {
		return getAttr( elementName, "id", attributes );
	}
	
	@Override
	public void characters( char[] ch, int start, int length ) {
		String content = new String( ch, start, length ).trim();
		
		if ( !"".equals( content ) ) {
			throw new IllegalStateException( "Not expecting character content (" + content + ") in this location" );
		}
	}
	
	@Override
	public void endDocument() {
		System.err.println( "Unexpected end of document" );
		controller.finish();
	}
	
	@Override
	public void endElement( String uri, String localName, String qName ) {
		throw new IllegalStateException( "Not expecting end of element " + qName + " in this location" );
	}
	
	@Override
	public void startDocument() {
		throw new IllegalStateException( "Unexpected start of document" );
	}
	
	@Override
	public void startElement( String uri, String localName, String qName, Attributes attributes ) {
		throw new IllegalStateException( "Not expecting start of element " + qName + " in this location" );
	}
}
