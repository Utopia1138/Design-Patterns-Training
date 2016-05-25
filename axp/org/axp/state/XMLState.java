package org.axp.state;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A parse state. Having multiple states allows the parser to (roughly) meet the Single Responsibility
 * Principle; one class for parsing users, another for parsing games, and so on. It also reduces the
 * amount of internal state we have to store, e.g. anything involving stacks of tags, which quickly
 * becomes a headache.
 * 
 * We subclass {@link DefaultHandler} in order to have the standard SAX XML-handling methods available
 * to each state.
 */
public abstract class XMLState extends DefaultHandler {
	protected final GameEventController controller;
	
	/**
	 * Construct against a particular context
	 * @param controller the main controller which handles the parsing
	 */
	public XMLState( GameEventController controller ) {
		this.controller = controller;
	}
	
	/**
	 * Convenience method to assert that an attribute exists and then get its value
	 * @param elementName the element currently being parsed
	 * @param attr the attribute key to get
	 * @param attributes the collection of attributes
	 * @return the value corresponding to <tt>attr</tt>
	 */
	protected static String getAttr( String elementName, String attr, Attributes attributes ) {
		if ( attributes == null || attributes.getValue( attr ) == null ) {
			throw new IllegalArgumentException( elementName + " without " + attr + '!' );
		}
		
		return attributes.getValue( attr );
	}
	
	protected static String getId( String elementName, Attributes attributes ) {
		return getAttr( elementName, "id", attributes );
	}
	
	/* Handle character data */
	@Override
	public void characters( char[] ch, int start, int length ) {
		String content = new String( ch, start, length ).trim();
		
		if ( !"".equals( content ) ) {
			throw new IllegalStateException( "Not expecting character content (" + content + ") in this location" );
		}
	}
	
	/* End of document, after final closing tag */
	@Override
	public void endDocument() {
		System.err.println( "Unexpected end of document" );
		controller.finish();
	}
	
	/* End tag of an element */
	@Override
	public void endElement( String uri, String localName, String qName ) {
		throw new IllegalStateException( "Not expecting end of element " + qName + " in this location" );
	}
	
	/* Start of document, before first opening tag */
	@Override
	public void startDocument() {
		throw new IllegalStateException( "Unexpected start of document" );
	}
	
	/* Start tag of an element */
	@Override
	public void startElement( String uri, String localName, String qName, Attributes attributes ) {
		throw new IllegalStateException( "Not expecting start of element " + qName + " in this location" );
	}
}
