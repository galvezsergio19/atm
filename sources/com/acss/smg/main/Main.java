
package com.acss.smg.main;
import com.acss.smg.model.AtmStatusModel;
import com.acss.smg.model.UserModel;
import com.acss.smg.service.impl.AtmStatusImplement;
import com.acss.smg.service.impl.UserLoginImplement;
import com.acss.smg.utility.Screen;
/*****************************************************/
import java.util.Scanner;
import java.io.IOException;
import java.text.ParseException;

public class Main {

	// Global Variables
	private static Scanner scanner = new Scanner(System.in);
	// Constructor
	private static Screen screen = new Screen();
	private static AtmStatusImplement atmStat = new AtmStatusImplement();
	private static UserLoginImplement userLogin = new UserLoginImplement();
	private static UserModel user = null;
	private static AtmStatusModel atmModel = null;
	
	
	public static void main(String[] args) throws IOException, InterruptedException, ParseException {
			
		try {
			// Accept two parameters : Account Number and Password
			if (args.length == 2) {
				//Set Models
				user = new UserModel(" " , Long.parseLong(args[0]), args[1], " ");
				atmModel = new AtmStatusModel();
				// Available or No Cash
				if (atmStat.checkStatus(atmModel) == true) { 
					// Chack user validity
					if (userLogin.validateUser(user) == false) {
						System.out.println("Please check your access.");
					} else {
						/* Valid Access for user */	
						boolean isExit = false;
						while(isExit == false) {
							screen.showScreen("Main");
							isExit = validateMainInput(user,atmModel);
							System.out.println(isExit);
						}
						new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
					}
				// Out of Order
				} else { 
					System.out.println("ATM is out of Order."); 
				}
			} else {
				System.out.println("Incomplete data found.");
			}
		} catch ( NumberFormatException e ) { 
			System.out.println("Account number not valid.");
		}
	}
	
	
	public static boolean validateMainInput(UserModel user, AtmStatusModel atmModel) throws IOException, InterruptedException, ParseException {
		
		System.out.print( "\n  Enter valid option: " );
		String input = scanner.nextLine();
		boolean isExit = false;	
		if (input.length() == 1) {
			switch (input.toUpperCase()) {
				case "D" :
					screen.validateDepositInput(user, atmModel);
					isExit = false;
					break;
				case "W" :
					screen.validateWithdrawInput(user, atmModel);
					isExit = false;
					break;
				case "F" : 
					screen.validateFastCashInput(user, atmModel);
					isExit = false;
					break;
				case "S" : 
					screen.validateStatementInput(user);
					isExit = false;
					break;
				case "T" :
					screen.validateTransferInput(user);
					isExit = false;
					break; 
				case "B" : 
					screen.validateBalanceInput(user);
					isExit = false;
					break; 
				case "R" : 
					screen.validateTransferReport(user);
					isExit = false;
					break; 
				case "X" :
					isExit = true; 
					break; 
				default  : 
					System.out.print("  Invalid Input. Please Enter to try again. ");
					input = scanner.nextLine();
					isExit = false;
					break; 
			}
		} else {
			System.out.print("  Invalid Input. Please Enter to try again. ");
			input = scanner.nextLine();
		}
		return isExit;
	}
}