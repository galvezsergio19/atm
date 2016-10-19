
package com.acss.smg.dao;
import com.acss.smg.model.AccountsModel;
import com.acss.smg.model.AtmStatusModel;
import com.acss.smg.model.UserModel;
/*****************************************************/
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;

public interface TransactionDAO {
	public void retrieveAccountInfo(UserModel user, AccountsModel accountsModel) throws IOException, ParseException;
	public void updateCurrentBalance(UserModel user, BigDecimal currentBalanceWithInput1) throws IOException;
	public ArrayList<String> retrieveCurrentBalance(UserModel user, BigDecimal currentBalanceWithInput) throws IOException;
	public void updateAtmStatusBalance(AtmStatusModel atmModel, BigDecimal AtmWithInput) throws IOException;
}