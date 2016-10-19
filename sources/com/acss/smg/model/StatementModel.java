
package com.acss.smg.model;
import java.math.BigDecimal;
public class StatementModel {

	/**************************************************************************/
	//  Variables
	/**************************************************************************/
	private String customerID;
	private String transType;
	private String dateAndTime;
	private BigDecimal amount;
	
	/**************************************************************************/
	//  Constructors
	/**************************************************************************/
	public StatementModel() {}
	public StatementModel(String customerID, String transType, String dateAndTime, BigDecimal amount) {
		this.customerID = customerID;
		this.transType = transType;
		this.dateAndTime = dateAndTime;
		this.amount = amount;
	}	
	
	/**************************************************************************/
	//  Setter
	/**************************************************************************/
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	public void setTransType(String transType) {
		this.transType = transType;
	}
	
	public void setDateAndTime(String dateAndTime)	{
		this.dateAndTime = dateAndTime;		
	}
	
	public void setAmount(BigDecimal amount) {	
		this.amount = amount;	
	}

	/**************************************************************************/
	//  Getter
	/**************************************************************************/
	public String getCustomerID() {
		return customerID;	
	}
		
	public String getTransType() {	
		return transType;	
	}
	
	public String getDateAndTime() {
		return dateAndTime;	
	}
	public BigDecimal getAmount() { 
		return amount;
	}
	
}