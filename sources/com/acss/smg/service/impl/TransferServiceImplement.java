
package com.acss.smg.service.impl;
import com.acss.smg.constant.Constant;
import com.acss.smg.dao.impl.TransactionDAOImplement;
import com.acss.smg.dao.impl.TransferDAOImplement;
import com.acss.smg.dao.impl.StatementDAOImplement;
import com.acss.smg.model.UserModel;
import com.acss.smg.service.TransferService;
import com.acss.smg.utility.Utility;
/*****************************************************/
import java.math.BigDecimal;
import java.io.IOException;
import java.text.ParseException;

public class TransferServiceImplement implements TransferService {

	// Constructor
	private Utility util = new Utility();
	private StatementDAOImplement statement = new StatementDAOImplement();
	private TransferDAOImplement transfer = new TransferDAOImplement();
	private TransactionDAOImplement transact = new TransactionDAOImplement();
	
	public boolean checkAccountNo(UserModel user, Long input) throws IOException {
		boolean isValidAccountNo = false;
		isValidAccountNo = transfer.retrieveAccountNo(user,input);
		return isValidAccountNo;	
	}
	
	public void transferAmount(UserModel user, BigDecimal validAmount) throws IOException{
		transact.updateCurrentBalance(user,validAmount);
	}
	
	public void writeStatement(UserModel user, Long accountNo, BigDecimal validAmount) throws IOException, ParseException {
		String amount = util.convertBigDecimalString(validAmount);
		statement.writeStatement(user.getCustomerID(), "transfer", amount);
		transfer.writeTransferLog(user, accountNo, validAmount);
	}

} 