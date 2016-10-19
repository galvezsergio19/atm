
package com.acss.smg.service.impl;
import com.acss.smg.dao.impl.TransferReportDAOImplement;
import com.acss.smg.model.UserModel;
import com.acss.smg.service.TransferReportService;
/*****************************************************/
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class TransferReportServiceImplement implements TransferReportService {

	// Constructor
	private TransferReportDAOImplement transReport = new TransferReportDAOImplement();
	
	public boolean checkRecord(UserModel user) throws IOException {
		boolean hasRecord = false;
		ArrayList<String> transferRec = new ArrayList<String>();	
		transferRec = transReport.retrieveTransferRecord(user);
		Iterator itr = transferRec.iterator();
		if(itr.hasNext()){
			while(itr.hasNext()) {
				Object element = itr.next();
				System.out.println("  "+element.toString());
				hasRecord = true;
			}
		transReport.createTransReport(user);
		}
		return hasRecord;
	}
	
}