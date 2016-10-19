
package com.acss.smg.dao.impl;
import com.acss.smg.constant.Constant;
import com.acss.smg.dao.StatementDAO;
import com.acss.smg.model.UserModel;
import com.acss.smg.utility.Utility;
/*****************************************************/
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Scanner;
public class StatementDAOImplement implements StatementDAO {

	// Constructors
	private Constant file = new Constant();
	private Utility util = new Utility();
	private Date dateNow = new Date();
	private SimpleDateFormat dateformat = new SimpleDateFormat(file.dateformat);
	// Reader and Writer
	private BufferedReader reader = null;
	private BufferedWriter writer = null;
	
	public ArrayList<String> retrieveStatementRecord(UserModel user) throws IOException {

		ArrayList<String> anArray = new ArrayList<String>();
		reader = new BufferedReader(new FileReader(file.statementFile));
		
		try {
			String readLine = reader.readLine();
			String customerID = user.getCustomerID();
			String[] splitted = null;
			
			// Read until file has value
			while (readLine != null) {
				//Save to ArrayList
				splitted = readLine.split("\\|");
				if ( customerID.equals(splitted[0]) ) {
					anArray.add(splitted[0] + "      " +
								splitted[1] + "           " +
								splitted[2] + "    " +
								splitted[3]);
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
		return anArray;
	}
	
	public void writeStatement(String customerID, String transType, String validAmount) throws IOException{
		
		try {
		
			String dateString = new String(dateformat.format(dateNow));
			writer = new BufferedWriter( new FileWriter(file.statementFile, true) );
			writer.write(customerID + "|" + transType + "|" + 
						  dateString + "|" + validAmount  + "\n");
			
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
	
	public void createStatementReport(UserModel user) throws IOException {
		
		try {
			// Create File with Format (mmddyyyy+accountnumber)
			String accountNoString = String.valueOf(user.getAccountNo());
			SimpleDateFormat dateformat = new SimpleDateFormat("MMddyyyy");
			String newFileName = new String(file.ReportLocation + dateformat.format(dateNow)+accountNoString+".report");
			writer = new BufferedWriter(new FileWriter(newFileName));
			reader = new BufferedReader(new FileReader(file.statementFile));
			String readLine = reader.readLine();
			
			while (readLine!=null) {
				String[] splitted = readLine.split("\\|");
				if (splitted[0].equals(user.getCustomerID())) {
					writer.write(readLine + "\n");
					writer.flush();
				}
				readLine = reader.readLine();
			}
			
		} catch (IOException e) {
			System.out.println("Error: File cannot be processed.");
		} finally {
			reader.close();
			writer.close();
		}
	}
	

}