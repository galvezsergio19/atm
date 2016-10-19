
package com.acss.smg.dao.impl;
import com.acss.smg.constant.Constant;
import com.acss.smg.dao.TransferReportDAO;
import com.acss.smg.model.UserModel;
import com.acss.smg.utility.Utility;
/*****************************************************/
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TransferReportDAOImplement implements TransferReportDAO {

	// Constructors
	private Constant file = new Constant();
	private Utility util = new Utility();
	private Date dateNow = new Date();
	// Reader and Writer
	private BufferedReader reader = null;
	private BufferedWriter writer = null;

	public ArrayList<String> retrieveTransferRecord(UserModel user) throws IOException {
		
		ArrayList<String> anArray = new ArrayList<String>();
		reader = new BufferedReader(new FileReader(file.transferFile));
		try {
			String readLine = reader.readLine();
			String customerID = user.getCustomerID();
			String[] splitted = null;
			
			// Read until file has value
			while (readLine != null) {
				//Save to ArrayList
				splitted = readLine.split("\\|");
				if ( customerID.equals(splitted[0]) ) {
					anArray.add(splitted[0] + "    " +
								splitted[1] + "   " +
								splitted[2] + "      " +
								splitted[3] + "    " +
								splitted[4]);
				}
				readLine = reader.readLine();
			}
			
		} catch (IOException e) {
		} finally {
			try {
				reader.close();
			} catch (NullPointerException e) {
				System.out.println("Error: Null pointer.");
			}
		}
		return anArray;
	}
	
	public void createTransReport(UserModel user) throws IOException {
		
		try {
			// Create File with Format (TR+mmddyyyy+accountnumber)
			String accountNoString = String.valueOf(user.getAccountNo());
			SimpleDateFormat dateformat = new SimpleDateFormat("MMddyyyy");
			String newFileName = new String(file.ReportLocation + "TR"+dateformat.format(dateNow)+accountNoString+".report");
			writer = new BufferedWriter(new FileWriter(newFileName));
			reader = new BufferedReader(new FileReader(file.transferFile));
			
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