
package com.acss.smg.model;
public class CustomerModel {

	/**************************************************************************/
	//  Variables
	/**************************************************************************/
	private String customerID;
	private String customerName;
	private String customerAddress;
	
	/**************************************************************************/
	//  Constructors
	/**************************************************************************/
	public CustomerModel(){}
	public CustomerModel( String customerID, String customerName, String customerAddress) {
		this.customerID = customerID;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
	}
	
	/**************************************************************************/
	//  Setter
	/**************************************************************************/
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	/**************************************************************************/
	//  Getter
	/**************************************************************************/
	public String getCustomerID() {
		return customerID;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public String getCustomerAddress() {
		return customerAddress;
	}
	
}