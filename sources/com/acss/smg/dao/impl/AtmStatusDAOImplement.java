
package com.acss.smg.dao.impl;
import com.acss.smg.constant.Constant;
import com.acss.smg.dao.AtmStatusDAO;
import com.acss.smg.model.AtmStatusModel;
import com.acss.smg.utility.Utility;
/********************************************/
import java.io.FileReader;
import java.io.BufferedReader;
import java.math.BigDecimal;
import java.io.IOException;
import java.text.ParseException;

public class AtmStatusDAOImplement implements AtmStatusDAO {

	private BufferedReader reader = null;
	private Utility util = new Utility();
	private Constant constant = new Constant();
	
	public void retrieveAtmInfo(AtmStatusModel atmModel) throws IOException, ParseException {
		
		try {
			reader = new BufferedReader(new FileReader(constant.atmFile));
			String lastReadLine = new String("");
			String readLine = reader.readLine();
			
			// Read until file has value
			while (readLine != null) {
				lastReadLine = readLine;
				readLine = reader.readLine();
			}
			// Pass atmstatus record to Model
			String[] splitted = lastReadLine.split("\\|");
			atmModel.setCurrentAmount(util.BigDecimalConvert(splitted[0]));
			atmModel.setAtmStat(splitted[1]);
			
		} catch (IOException e) {
			System.out.println("Error: File cannot be processed.");
		} finally {
			reader.close();
		}
	}

}