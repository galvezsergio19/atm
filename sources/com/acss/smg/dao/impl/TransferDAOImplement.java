
package com.acss.smg.dao.impl;
import com.acss.smg.constant.Constant;
import com.acss.smg.dao.TransferDAO;
import com.acss.smg.model.UserModel;
import com.acss.smg.utility.Utility;
/*****************************************************/
import java.math.BigDecimal;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransferDAOImplement implements TransferDAO {

	// Constructor
	private Constant file = new Constant();
	private Utility util = new Utility();
	private Date dateNow = new Date();
	private SimpleDateFormat dateformat = new SimpleDateFormat(file.dateformat);
	// Reader and Writer
	private BufferedReader reader = null;
	private BufferedWriter writer = null;
		
	public boolean retrieveAccountNo(UserModel user, Long input) throws IOException {
		
		boolean isValidAccountNo = false;
		try {
			reader = new BufferedReader(new FileReader(file.userFile));
			String readLine = reader.readLine();
			
			while(readLine!=null) {
				String[] splitted = readLine.split("\\|");
				if (input==Long.parseLong(splitted[1]) && input!=user.getAccountNo()) {
					isValidAccountNo = true;
					break;
				}
				readLine = reader.readLine();
			}
			
		} catch (IOException e ) {
			System.out.println("Error: File cannot be processed.");
		} finally {
			reader.close();
		}
		return isValidAccountNo;
	}
	
	
	public void writeTransferLog(UserModel user, Long accountNo, BigDecimal validAmount) throws IOException {

		try {
			String dateString = new String(dateformat.format(dateNow));
			writer = new BufferedWriter(new FileWriter(file.transferFile, true));
			writer.write(user.getCustomerID() + "|" + String.valueOf(user.getAccountNo()) + "|" +
						accountNo + "|" + dateString + "|" + util.convertBigDecimalString(validAmount) + "\n");
		} catch (IOException e) {
			System.out.println("Error: File cannot be processed.");
		} finally {
			writer.close();
		}
	}


}