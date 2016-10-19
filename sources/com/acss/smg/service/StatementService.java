
package com.acss.smg.service;
import com.acss.smg.model.UserModel;
/*****************************************************/
import java.io.IOException;

public interface StatementService {
	public boolean checkRecord(UserModel user) throws IOException;
}