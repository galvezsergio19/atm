
package com.acss.smg.dao;
import com.acss.smg.model.UserModel;
/*****************************************************/
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public interface StatementDAO {
	public ArrayList<String> retrieveStatementRecord(UserModel user) throws IOException;
	public void writeStatement(String customerID, String transType, String amount) throws IOException;
	public void createStatementReport(UserModel user) throws IOException;
}