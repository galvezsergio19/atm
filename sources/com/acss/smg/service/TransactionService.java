
package com.acss.smg.service;
import com.acss.smg.model.AtmStatusModel;
import com.acss.smg.model.UserModel;
/*****************************************************/
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;

public interface TransactionService {
	public boolean checkBalanceForDeposit(BigDecimal validAmount, UserModel user, AtmStatusModel atmModel) throws IOException, ParseException;
	public boolean checkBalanceForWithdraw(BigDecimal validAmount, UserModel user, AtmStatusModel atmModel) throws IOException, ParseException;
	public BigDecimal getCurrentBalanceWithInput();
	public BigDecimal getAtmBalanceWithInput();
	public void transact(String transType, String validAmount, BigDecimal balanceWithInput, BigDecimal atmWithInput, UserModel user, AtmStatusModel atmModel) throws IOException;
}