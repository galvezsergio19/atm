
package com.acss.smg.dao;
import com.acss.smg.model.UserModel;
/*****************************************************/
import java.io.IOException;
import java.util.ArrayList;
import java.math.BigDecimal;

public interface TransferReportDAO {
	public ArrayList<String> retrieveTransferRecord(UserModel user) throws IOException;
	public void createTransReport(UserModel user) throws IOException;
}