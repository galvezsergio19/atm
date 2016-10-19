
package com.acss.smg.model;
import java.math.BigDecimal;
public class TransferReportModel {

	/**************************************************************************/
	//  Variables
	/**************************************************************************/
	private String customerID;
	private long accountNoFrom;
	private long accountNoTo;
	private String transferDate;
	private BigDecimal amount;
	
	/**************************************************************************/
	//  Constructors
	/**************************************************************************/
	public TransferReportModel() {}
	public TransferReportModel(String customerID, long accountNoFrom, long accountNoTo, String transferDate, BigDecimal amount) {
		this.customerID = customerID;
		this.accountNoFrom = accountNoFrom;
		this.accountNoTo = accountNoTo;
		this.transferDate = transferDate;
		this.amount = amount;
	}	
	
	/**************************************************************************/
	//  Setter
	/**************************************************************************/
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	public void setAccountNoFrom(long accountNoFrom) {
		this.accountNoFrom = accountNoFrom;
		
	}public void setAccountNoTo(long accountNoTo) {
		this.accountNoTo = accountNoTo;
	}
	
	public void setTransferDate(String transferDate)	{
		this.transferDate = transferDate;		
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
		
	public long getAccountNoFrom() {	
		return accountNoFrom;	
	}	
	public long getAccountNoTo() {	
		return accountNoTo;	
	}
	
	public String getTransferDate() {
		return transferDate;	
	}
	public BigDecimal getAmount() { 
		return amount;
	}
	
}