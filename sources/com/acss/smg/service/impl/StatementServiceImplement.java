
package com.acss.smg.service.impl;
import com.acss.smg.constant.Constant;
import com.acss.smg.dao.impl.StatementDAOImplement;
import com.acss.smg.model.UserModel;
import com.acss.smg.service.StatementService;
/*****************************************************/
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class StatementServiceImplement implements StatementService {

	// Constructor
	private StatementDAOImplement statement = new StatementDAOImplement();
	
	public boolean checkRecord(UserModel user) throws IOException {
		boolean hasRecord = false;
		ArrayList<String> statementRec = new ArrayList<String>();
		statementRec = statement.retrieveStatementRecord(user);
		Iterator itr = statementRec.iterator();
		if(itr.hasNext()){
			while(itr.hasNext()) {
				Object element = itr.next();
				System.out.println("  "+element.toString());
				hasRecord = true;
			}
		statement.createStatementReport(user);
		}
		return hasRecord;
	}
	
}