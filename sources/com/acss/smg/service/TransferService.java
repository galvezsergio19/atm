
package com.acss.smg.service;
import com.acss.smg.model.UserModel;
/*****************************************************/
import java.math.BigDecimal;
import java.io.IOException;
import java.text.ParseException;

public interface TransferService {
	public boolean checkAccountNo(UserModel user, Long input) throws IOException;
	public void transferAmount(UserModel user, BigDecimal validAmount) throws IOException;
	public void writeStatement(UserModel user,  Long accountNo, BigDecimal validAmount) throws IOException, ParseException;
}