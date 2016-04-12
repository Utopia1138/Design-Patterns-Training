
package com.spg.chapter7.bank;

public class APACSResponse {

	private String	tid;
	private String	authcode;
	private String	message;

	public String getTid() {
		return tid;
	}

	public void setTid( String tid ) {
		this.tid = tid;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode( String authcode ) {
		this.authcode = authcode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage( String message ) {
		this.message = message;
	}

}
