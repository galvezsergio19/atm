
package com.acss.smg.service.impl;
import com.acss.smg.constant.Constant;
import com.acss.smg.dao.impl.BalanceDAOImplement;
import com.acss.smg.dao.impl.StatementDAOImplement;
import com.acss.smg.dao.impl.TransactionDAOImplement;
import com.acss.smg.model.AccountsModel;
import com.acss.smg.model.UserModel;
import com.acss.smg.service.BalanceService;
import com.acss.smg.utility.Utility;
/*****************************************************/
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

public class BalanceServiceImplement implements BalanceService {

	// Constructor
	private Utility util = new Utility();
	private AccountsModel accountsModel = new AccountsModel();
	private BalanceDAOImplement balance = new BalanceDAOImplement();
	private StatementDAOImplement statement = new StatementDAOImplement();
	private TransactionDAOImplement transaction = new TransactionDAOImplement();
	
	public boolean checkRecord(UserModel user) throws IOException {
		boolean hasRecord = false;
		ArrayList<String> balanceRec = new ArrayList<String>();
		balanceRec = balance.retrieveBalanceRecord(user);
		Iterator itr = balanceRec.iterator();
		
		if(itr.hasNext()){
			while(itr.hasNext()) {
				Object element = itr.next();
				System.out.println("  "+element.toString());
				hasRecord = true;
			}
		balance.createBalance(user);
		}
		return hasRecord;
	}
	
	public void transact(String transType, UserModel user) throws IOException, ParseException {
		transaction.retrieveAccountInfo(user,accountsModel);
		String bigDecimalBalance = util.convertBigDecimalString(accountsModel.getCurrentBalance());
		statement.writeStatement(user.getCustomerID(), transType, bigDecimalBalance);
	}
	
}