package com.spg.chapter7.dpg;

import java.util.HashMap;
import java.util.Map;

/**
 * A very simple implementation of some kind of XML-like structure
 * 
 * @author E043175
 *
 */
public class DPGMessageElement {

	private String name;
	private String value;
	private Map<String,DPGMessageElement> subElements;
	
	public DPGMessageElement( String name, String value ) {
		this.name = name;
		this.value = value;
	}
	
	public DPGMessageElement( String name ) {
		this.name = name;
		subElements = new HashMap<String,DPGMessageElement>();
	}
	
	public void addSubElement( DPGMessageElement element ) {
		// Not bothered with anything more complex than this
		if ( subElements == null ) {
			return;
		}
		
		subElements.put( element.getName(), element );
	}
	
	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
	
	public DPGMessageElement getSubElement( String name ) {
		return subElements.get( name );
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder(); 
		output.append( "<" + name + ">" );
		if ( value != null ) output.append( this.value );
		if ( subElements != null ) {
			output.append( "\n" );
			for ( String subName : subElements.keySet() ) {
				output.append( subElements.get( subName ).toString() );
			}
		}
		output.append( "</" + name + ">" );
		output.append( "\n" );

		return output.toString();
	}
}
