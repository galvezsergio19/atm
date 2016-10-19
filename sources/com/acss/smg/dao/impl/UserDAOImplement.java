
package com.acss.smg.dao.impl;
import com.acss.smg.constant.Constant;
import com.acss.smg.dao.UserDAO;
import com.acss.smg.model.UserModel;
/*****************************************************/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UserDAOImplement implements UserDAO {
	
	// COnstructor 
	private Constant file = new Constant();
	private UserModel userLogin = new UserModel();
	// Reader 
	private BufferedReader reader = null;
	
	public UserModel retrieveData(UserModel user) throws IOException {
		
		try {
			reader = new BufferedReader(new FileReader(file.userFile));
			String readLine = reader.readLine();
	
			while (readLine != null) {
				String[] splitted = readLine.split("\\|");
				if (user.getAccountNo() == Long.parseLong(splitted[1])){
					userLogin.setCustomerID(splitted[0]);
					userLogin.setAccountNo(Long.parseLong(splitted[1]));
					userLogin.setPassword(splitted[2]);
					userLogin.setAccountType(splitted[3]);
					break;
				}
				readLine = reader.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: File cannot be processed.");
		} finally {
			try {
				reader.close();
			} catch (NullPointerException e) {
				System.out.println("Error: Null pointer.");
			}
		}
	return userLogin;
	}
}