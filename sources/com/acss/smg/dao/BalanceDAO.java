
package com.acss.smg.dao;
import com.acss.smg.model.UserModel;
/*******************************************************/
import java.io.IOException;
import java.util.ArrayList;
import java.math.BigDecimal;

public interface BalanceDAO {
	public ArrayList<String> retrieveBalanceRecord(UserModel user) throws IOException;
	public void createBalance(UserModel user) throws IOException;
}