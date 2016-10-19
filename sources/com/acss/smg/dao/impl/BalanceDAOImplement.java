
package com.acss.smg.dao.impl;
import com.acss.smg.dao.BalanceDAO;
import com.acss.smg.constant.Constant;
import com.acss.smg.model.UserModel;
import com.acss.smg.utility.Utility;
/*******************************************************/
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BalanceDAOImplement implements BalanceDAO {
	
	// Constructors
	private Constant file = new Constant();
	private Utility util = new Utility();
	// Reader and Writer
	private BufferedReader reader = null;
	private BufferedWriter writer = null;
	
	public ArrayList<String> retrieveBalanceRecord(UserModel user) throws IOException {
		
		ArrayList<String> anArray = new ArrayList<String>();
		reader = new BufferedReader(new FileReader(file.accountFile));
		
		try {
			String readLine = reader.readLine();
			String customerID = user.getCustomerID();
			String[] splitted = null;
			
			// Read until file has value
			while (readLine != null) {
				//Save to ArrayList
				splitted = readLine.split("\\|");
				if ( customerID.equals(splitted[0]) ) {
					anArray.add(splitted[0] + "       " +
								splitted[1] );
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
	
	public void createBalance(UserModel user) throws IOException {
		
		try {
			// Create File with Format (B+mmddyyyy+accountnumber)
			Date dateNow = new Date();
			String accountNoString = String.valueOf(user.getAccountNo());
			SimpleDateFormat dateformat = new SimpleDateFormat("MMddyyyy");
			String newFileName = new String(file.ReportLocation+"B"+dateformat.format(dateNow)+accountNoString+".report");
			writer = new BufferedWriter(new FileWriter(newFileName));
			reader = new BufferedReader(new FileReader(file.accountFile));
			String read = reader.readLine();
			
			while (read!=null) {
				String[] splitted = read.split("\\|");
				if (splitted[0].equals(user.getCustomerID())) {
					writer.write(read + "\n");
					writer.flush();
				}
				read = reader.readLine();
			}
			
		} catch (IOException e) {
			System.out.println("Error: File cannot be processed.");
		} finally {
			reader.close();
			writer.close();
		}
	}
	

}