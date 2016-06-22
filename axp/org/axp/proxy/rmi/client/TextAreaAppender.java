package org.axp.proxy.rmi.client;

import java.io.IOException;

import javax.swing.JTextArea;

public class TextAreaAppender implements Appendable {
	private final JTextArea textArea;
	
	public TextAreaAppender( final JTextArea textArea ) {
		this.textArea = textArea;
	}

	@Override
	public Appendable append( CharSequence csq ) throws IOException {
		textArea.append( csq.toString() );
		return this;
	}

	@Override
	public Appendable append( CharSequence csq, int start, int end ) throws IOException {
		textArea.append( csq.subSequence( start, end ).toString() );
		return this;
	}

	@Override
	public Appendable append( char c ) throws IOException {
		textArea.append( "" + c );
		return this;
	}
}