
package com.acss.smg.model;
import java.math.BigDecimal;
public class AccountsModel {

	/**************************************************************************/
	//  Variables
	/**************************************************************************/
	private String customerID;
	private BigDecimal currentBalance;
	
	/**************************************************************************/
	//  Constructors
	/**************************************************************************/
	public AccountsModel(){}
	public AccountsModel(String customerID, BigDecimal currentBalance) {
		this.customerID = customerID;
		this.currentBalance = currentBalance;
	}
	
	/**************************************************************************/
	//  Setter
	/**************************************************************************/
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	/**************************************************************************/
	//  Getter
	/**************************************************************************/
	public String getCustomerID() {
		return customerID;
	}
	
	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}
	
}