
package com.acss.smg.dao.impl;
import com.acss.smg.constant.Constant;
import com.acss.smg.dao.TransactionDAO;
import com.acss.smg.model.AccountsModel;
import com.acss.smg.model.AtmStatusModel;
import com.acss.smg.model.UserModel;
import com.acss.smg.utility.Utility;
/*****************************************************/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

public class TransactionDAOImplement implements TransactionDAO {
	
	// Constructor
	private Constant file = new Constant();
	private Utility util = new Utility();
	// Reader and Writer
	private BufferedReader reader = null;
	private BufferedWriter writer = null;

	public void retrieveAccountInfo(UserModel user, AccountsModel accountsModel) throws IOException, ParseException {

		try {
			reader = new BufferedReader(new FileReader(file.accountFile));
			String readLine = reader.readLine();
			String customerID = user.getCustomerID();
			String[] splitted = null;
			
			// Read until file has value
			while (readLine != null) {
				splitted = readLine.split("\\|");
				if ( customerID.equals(splitted[0]) ) { break; }
				readLine = reader.readLine();
			}
			// Pass atmstatus record to Model
			accountsModel.setCustomerID(splitted[0]);
			BigDecimal currentBalance = util.BigDecimalConvert(splitted[1]);
			accountsModel.setCurrentBalance(currentBalance);
			
		} catch (IOException e) {
			System.out.println("Error: File cannot be processed.");
		} finally {
			try {
				reader.close();
			} catch (NullPointerException e) {
				System.out.println("Error: Null pointer.");
			}
		}
	}
	
	public ArrayList<String> retrieveCurrentBalance(UserModel user, BigDecimal currentBalanceWithInput) throws IOException {
							
		ArrayList<String> accountsRecord = new ArrayList<String>();
		try {
			reader = new BufferedReader( new FileReader(file.accountFile) );
			String readLine = reader.readLine();
			String customerID = user.getCustomerID();
			String[] splitted = null;
			String updatedBalance = new String("");
			
			// Read until file has value
			while (readLine != null) {
			
				//Save to ArrayList
				splitted = readLine.split("\\|");
				if ( customerID.equals(splitted[0]) ) { 
					updatedBalance = customerID + "|" + 
					util.convertBigDecimalString(currentBalanceWithInput);
					accountsRecord.add(updatedBalance);
				} else {
					accountsRecord.add(readLine);
				}
				readLine = reader.readLine();
			}
			
		} catch (IOException e) {
			System.out.println("Error: File cannot be processed.");
		} finally {
			try {
				reader.close();
			} catch (NullPointerException e) {
				System.out.println("Error: Null pointer.");
			}
		}
		return accountsRecord;
	}
	
	public void updateCurrentBalance(UserModel user, BigDecimal currentBalanceWithInput1) throws IOException {
				
		try {
			ArrayList accountsRecord = retrieveCurrentBalance(user, currentBalanceWithInput1);
			writer = new BufferedWriter( new FileWriter(file.accountFile) );
			
			// Write the ArrayList to File
			Iterator itr = accountsRecord.iterator();
			while(itr.hasNext()){
				Object element = itr.next();
				writer.write(element.toString() + "\n"); 
			}
			
		} catch (IOException e) {
			System.out.println("Error: File cannot be processed.");
		} finally {
			try {
				writer.flush();
				writer.close();
			} catch (NullPointerException e) {
				System.out.println("Error: Null pointer.");
			}
		}
	}
	
	public void updateAtmStatusBalance(AtmStatusModel atmModel, BigDecimal AtmWithInput) throws IOException {

		String status = new String("");
		writer = new BufferedWriter(new FileWriter(file.atmFile));
		try {
			if (AtmWithInput.compareTo(new BigDecimal("0")) == 1) { 
				status = "A"; 
			} else {
				status = "NC";
			}
			writer.write(util.convertBigDecimalString(AtmWithInput) + "|" + status);
			
		} catch (IOException e) {
			System.out.println("Error: File cannot be processed.");
		} finally {
			try {
				writer.flush();
				writer.close();
			} catch (NullPointerException e) {
				System.out.println("Error: Null pointer.");
			}
		}
	}
	
	
}