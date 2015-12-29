package com.big.dorson.entity;

public class Transaction {

	private String PAN;
	private String expiry;
	private String currency;
	private String amount;
	
	private String authcode;
	private long authTimestamp;
	
	public void setAuthcode( String authcode ) {
	  this.authcode = authcode;
  }

  public String getAuthcode() {
	  return authcode;
  }

  public void setAuthTimestamp( long authTimestamp ) {
	  this.authTimestamp = authTimestamp;
  }

  public long getAuthTimestamp() {
	  return authTimestamp;
  }

	
  public String getPAN() {
  	return PAN;
  }

	
  public void setPAN( String pAN ) {
  	PAN = pAN;
  }

	
  public String getExpiry() {
  	return expiry;
  }

	
  public void setExpiry( String expiry ) {
  	this.expiry = expiry;
  }

	
  public String getCurrency() {
  	return currency;
  }

	
  public void setCurrency( String currency ) {
  	this.currency = currency;
  }

	
  public String getAmount() {
  	return amount;
  }

	
  public void setAmount( String amount ) {
  	this.amount = amount;
  }

}
