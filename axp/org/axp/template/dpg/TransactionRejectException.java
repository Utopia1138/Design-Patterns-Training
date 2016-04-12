package org.axp.template.dpg;

public class TransactionRejectException extends Exception {
	private static final long serialVersionUID = -4076737718279747886L;
	private int code;

	public TransactionRejectException( int code, String message ) {
		super( message );
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
}
