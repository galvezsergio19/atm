
package com.acss.smg.dao;
import com.acss.smg.model.UserModel;
/*****************************************************/
import java.math.BigDecimal;
import java.io.IOException;

public interface TransferDAO {
	public boolean retrieveAccountNo(UserModel user, Long input) throws IOException;
	public void writeTransferLog(UserModel user, Long accountNo, BigDecimal validAmount) throws IOException;
}