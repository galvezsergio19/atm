
package com.acss.smg.model;
public class UserModel {

	/**************************************************************************/
	//  Variables
	/**************************************************************************/
	private String customerID;
	private long accountNo;
	private String password;
	private String accountType;
	
	/**************************************************************************/
	//  Constructors
	/**************************************************************************/
	public UserModel() {}
	public UserModel(String customerID, long accountNo, String password, String accountType) {
		this.customerID = customerID;
		this.accountNo = accountNo;
		this.password = password;
		this.accountType = accountType;
	}	
	
	/**************************************************************************/
	//  Setter
	/**************************************************************************/
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	
	public void setPassword(String password)	{
		this.password = password;		
	}
	
	public void setAccountType(String accountType) {	
		this.accountType = accountType;	
	}

	/**************************************************************************/
	//  Getter
	/**************************************************************************/
	public String getCustomerID() {
		return customerID;	
	}
		
	public long getAccountNo() {	
		return accountNo;	
	}
	
	public String getPassword() {
		return password;	
	}
	public String getAccountType() { 
		return accountType;
	}
	
}