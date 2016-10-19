
package com.acss.smg.service.impl;
import com.acss.smg.constant.Constant;
import com.acss.smg.dao.impl.AtmStatusDAOImplement;
import com.acss.smg.dao.impl.StatementDAOImplement;
import com.acss.smg.dao.impl.TransactionDAOImplement;
import com.acss.smg.model.AtmStatusModel;
import com.acss.smg.model.AccountsModel;
import com.acss.smg.model.UserModel;
import com.acss.smg.service.TransactionService;
/*****************************************************/
import java.math.BigDecimal;
import java.io.IOException;
import java.text.ParseException;

public class TransactionServiceImplement implements TransactionService {

	// Constructor
	private Constant file = new Constant();
	private AccountsModel accountsModel = new AccountsModel();
	private AtmStatusDAOImplement atmDAO = new AtmStatusDAOImplement();
	private TransactionDAOImplement transaction = new TransactionDAOImplement();
	private StatementDAOImplement statement = new StatementDAOImplement();
	// Variables
	private BigDecimal currentBalance;
	private BigDecimal atmBalance;
	
	public boolean checkBalanceForDeposit(BigDecimal validAmount, UserModel user, AtmStatusModel atmModel) throws IOException, ParseException{
		
		transaction.retrieveAccountInfo(user,accountsModel);
		atmDAO.retrieveAtmInfo(atmModel);	
		BigDecimal bigDecimalBalance = accountsModel.getCurrentBalance();
		boolean isValidTransaction = false;
		
		// Amount should not be less than or equal to zero
		if (bigDecimalBalance.compareTo(new BigDecimal("0")) == 1) {
			// Pass the Model Values to validate
			currentBalance = validAmount.add(accountsModel.getCurrentBalance());
			// Validate Customer current balance
			if (currentBalance.compareTo(file.maxBal) == -1) { 
				// Validate ATM's Remaining amount
				atmBalance = validAmount.add(atmModel.getCurrentAmount());
				if (atmBalance.compareTo(file.maxAtm) == -1) { 
					isValidTransaction = true; 
				}
			}
		}
		return isValidTransaction;
	}
	
	public boolean checkBalanceForWithdraw(BigDecimal validAmount, UserModel user, AtmStatusModel atmModel) throws IOException, ParseException{
		
		transaction.retrieveAccountInfo(user,accountsModel);
		atmDAO.retrieveAtmInfo(atmModel);	
		BigDecimal bigDecimalBalance = accountsModel.getCurrentBalance();
		BigDecimal bigDecimalAmount = atmModel.getCurrentAmount();
		boolean isValidTransaction = false;
		
		// Amount should be greateer than Balance
		if (bigDecimalBalance.compareTo(file.minBal) == 1) {
			// Pass the Model Values to validate
			if (validAmount.compareTo(bigDecimalBalance.subtract(file.minBal)) != 1) {
				currentBalance = bigDecimalBalance.subtract(validAmount);
				atmBalance = bigDecimalAmount.subtract(validAmount);
				isValidTransaction = true;
			}
		}
		return isValidTransaction;
	}
	
	public BigDecimal getCurrentBalanceWithInput() {
		return currentBalance;
	}
	
	public BigDecimal getAtmBalanceWithInput() {
		return atmBalance;
	}
	
	// DEPOSIT/WITHDRAW METHOD
	public void transact(String transType, String validAmount, BigDecimal balanceWithInput, BigDecimal atmWithInput, UserModel user, AtmStatusModel atmModel) throws IOException {
		transaction.updateCurrentBalance(user, balanceWithInput);
		transaction.updateAtmStatusBalance(atmModel, atmWithInput);
		statement.writeStatement(user.getCustomerID(), transType, validAmount);
	}
	
}