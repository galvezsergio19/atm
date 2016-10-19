
package com.acss.smg.constant;
/****************************************/
import java.math.BigDecimal;

public class Constant {

	public static final String FileLocation 	= "resources\\";
	public static final String ReportLocation	= "reports\\";
	
	public static final String accountFile 		= FileLocation + "accounts.flat";
	public static final String atmFile			= FileLocation + "atmstatus.flat";
	public static final String customerFile 	= FileLocation + "customer.flat";
	public static final String statementFile 	= FileLocation + "statement.flat";
	public static final String transferFile 	= FileLocation + "transferreport.flat";
	public static final String userFile 		= FileLocation + "user.flat";
	
	// Date
	public static final String dateformat = "MM/dd/yyyy hh:mm:ss a";

	// Max and Min Balance 
	public static final BigDecimal maxBal = new BigDecimal("1000000000");
	public static final BigDecimal minBal = new BigDecimal("500");
	public static final BigDecimal maxAtm = new BigDecimal("500000000000000000");
	
}