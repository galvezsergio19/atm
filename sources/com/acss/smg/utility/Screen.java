
package com.acss.smg.utility;
import com.acss.smg.service.impl.StatementServiceImplement;
import com.acss.smg.service.impl.TransactionServiceImplement;
import com.acss.smg.service.impl.BalanceServiceImplement;
import com.acss.smg.service.impl.TransferReportServiceImplement;
import com.acss.smg.service.impl.TransferServiceImplement;
import com.acss.smg.service.impl.UserLoginImplement;
import com.acss.smg.dao.impl.TransactionDAOImplement;
import com.acss.smg.model.AccountsModel;
import com.acss.smg.model.AtmStatusModel;
import com.acss.smg.model.UserModel;
import com.acss.smg.utility.Utility;
/*****************************************************/
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Scanner;

public class Screen {

	// Constructors
	private static Utility util = new Utility();
	private static StatementServiceImplement statement = new StatementServiceImplement();
	private static TransactionServiceImplement transaction = new TransactionServiceImplement();
	private static BalanceServiceImplement balance = new BalanceServiceImplement();
	private static TransferReportServiceImplement transReport = new TransferReportServiceImplement();
	private static TransferServiceImplement transfer = new TransferServiceImplement();
	private static TransactionDAOImplement transactionDAO = new TransactionDAOImplement();
	// Variables
	private static Scanner scanner = new Scanner(System.in);
	private static String[] fastCashAmount = { "500", "1000", "2000", "3000",
											  "4000", "5000", "10000", "20000" };
	
	public static void showScreen(String screenCode) throws IOException, InterruptedException {
		
		switch(screenCode.toUpperCase()) {
		case "MAIN" :
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			System.out.println("-----------------------------------------------");
			System.out.println("                  -Menu Screen-                ");
			System.out.println("-----------------------------------------------");
			System.out.println("  D   -   Deposit       Transfer       -   T   ");
			System.out.println("  W   -   Withdraw      Balance        -   B   ");
			System.out.println("  F   -   Fast Cash     Trans Report   -   R   ");
			System.out.println("  S   -   Statement     Exit           -   X   ");
			System.out.println("-----------------------------------------------");
			break;
		case "FAST CASH" :
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			System.out.println("-----------------------------------------------");
			System.out.println("                   -Fast Cash-                 ");
			System.out.println("-----------------------------------------------");
			System.out.println("  1   -   P500.00       P4,000.00      -   5   ");
			System.out.println("  2   -   P1,000.00     P5,000.00      -   6   ");
			System.out.println("  3   -   P2,000.00     P10,000.00     -   7   ");
			System.out.println("  4   -   P3,000.00     P20,000.00     -   8   ");
			System.out.println("-----------------------------------------------");
			System.out.println("  Q   -   Back                                 ");
			System.out.println("-----------------------------------------------");
			break;
		case "DEPOSIT" :
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			System.out.println("-----------------------------------------------");
			System.out.println("                    -Deposit-                  ");
			System.out.println("-----------------------------------------------");
			System.out.println("  Q               -   Back                     ");
			System.out.println("  Amount Format   -   P000,000.00              ");
			System.out.println("-----------------------------------------------");
			System.out.println("                                               ");
			System.out.print  ("  Enter Valid Option/Amount : "                 );
			break;
		case "WITHDRAW" :
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			System.out.println("-----------------------------------------------");
			System.out.println("                    -Withdraw-                 ");
			System.out.println("-----------------------------------------------");
			System.out.println("  Q               -   Back                     ");
			System.out.println("  Amount Format   -   P000,000.00              ");
			System.out.println("-----------------------------------------------");
			System.out.println("                                               ");
			System.out.print  ("  Enter Valid Option/Amount : "                 );
			break;	
		case "STATEMENT" :
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			System.out.println("---------------------------------------------------------------------");
			System.out.println("                            -Statement-                              ");
			System.out.println("---------------------------------------------------------------------");
			System.out.println("  Q - Back                                                           ");
			System.out.println("---------------------------------------------------------------------");
			System.out.println("                                                                     ");
			break;	
		case "TRANSFER REPORT" :
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("                            -Transfer Report-                                ");
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("  Q - Back                                                                   ");
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("                                                                             ");
			break;	
		case "BALANCE" :
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			System.out.println("-----------------------------------------------");
			System.out.println("                     -Balance-                 ");
			System.out.println("-----------------------------------------------");
			System.out.println("  Q               -   Back                     ");
			System.out.println("-----------------------------------------------");
			System.out.println("                                               ");
			break;
		case "TRANSFER" :
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			System.out.println("-----------------------------------------------");
			System.out.println("                    -Transfer-                 ");
			System.out.println("-----------------------------------------------");
			System.out.println("  Q               -   Back                     ");
			System.out.println("  Amount Format   -   P000,000.00              ");
			System.out.println("-----------------------------------------------");
			System.out.println("                                               ");
			System.out.print  ("  Enter Account Number : "                 );
			break;
		}	
	}
	
	public static void validateDepositInput(UserModel user, AtmStatusModel atmModel) throws IOException, InterruptedException, ParseException {
		
		String input = new String("");
		String inputBack = new String("");
		BigDecimal validAmount = new BigDecimal(0);
		boolean processComplete = false;
		
		while( !(input.equalsIgnoreCase("Q")) ) {
			showScreen("Deposit");
			input = scanner.nextLine();
			validAmount = util.BigDecimalConvert(input);
			
			if (validAmount.compareTo(new BigDecimal("0")) == 1 ) {
				// Check the Amount + Balance
				processComplete = transaction.checkBalanceForDeposit(validAmount, user, atmModel);
				if ( processComplete == true ) {
					
					BigDecimal currentBalanceWithInput = transaction.getCurrentBalanceWithInput();
					BigDecimal atmBalanceWithInput = transaction.getAtmBalanceWithInput();
					System.out.println("  New Balance of " + user.getCustomerID() + " : " + 
															String.valueOf(currentBalanceWithInput));
					System.out.println("  New ATM Balance : " + String.valueOf(atmBalanceWithInput));
					inputBack = scanner.nextLine();
					
					transaction.transact("deposit ", util.convertBigDecimalString(validAmount),
										currentBalanceWithInput, atmBalanceWithInput,
										user,atmModel);
					System.out.print("  Deposit Amount Successfully. ");
					inputBack = scanner.nextLine();
				} else {
					System.out.print("  Amount not valid for processing. Please try again. ");
					inputBack = scanner.nextLine();
				}
			}
		}
	}
	
	public static void validateWithdrawInput(UserModel user,  AtmStatusModel atmModel) throws IOException, InterruptedException, ParseException {
	
		String input = new String("");
		String inputBack = new String("");
		BigDecimal validAmount = new BigDecimal(0);
		boolean processComplete = false;
		
		while( !(input.equalsIgnoreCase("Q")) ) {
			showScreen("Withdraw");
			input = scanner.nextLine();
			validAmount = util.BigDecimalConvert(input);

			if (validAmount.compareTo(new BigDecimal("0")) == 1 ) {
				if (atmModel.getAtmStat().equals("A")) {
					// Check the Amount - Balance
					processComplete = transaction.checkBalanceForWithdraw(validAmount, user, atmModel);
					if ( processComplete == true ) {
						
						BigDecimal currentBalanceWithInput = transaction.getCurrentBalanceWithInput();
						BigDecimal atmBalanceWithInput = transaction.getAtmBalanceWithInput();
						System.out.println("  New Balance of " + user.getCustomerID() + " : " + 
																String.valueOf(currentBalanceWithInput));
						System.out.println("  New ATM Balance : " + String.valueOf(atmBalanceWithInput));
						inputBack = scanner.nextLine();
						transaction.transact("withdraw", util.convertBigDecimalString(validAmount),
											currentBalanceWithInput, atmBalanceWithInput,
											user,atmModel);
						System.out.print("  Withdraw Amount Successfully. ");
						inputBack = scanner.nextLine();
					} else {
						System.out.print("  Amount not valid for processing. Please try again. ");
						inputBack = scanner.nextLine();
					}
				} else {
					if (!(input.equalsIgnoreCase("Q"))) {
						System.out.print("  No Cash Available for Withdrawing. Please try again later ");
						inputBack = scanner.nextLine();
					}
				}
			}
		}
	}
	
	public static void validateFastCashInput(UserModel user,  AtmStatusModel atmModel) throws IOException, InterruptedException, ParseException {
		
		String input = new String("");
		String inputBack = new String("");
		BigDecimal validAmount = new BigDecimal(0);
		boolean processComplete = false;
		
		while( !(input.equalsIgnoreCase("Q")) ) {
			showScreen("Fast Cash");
			System.out.print("\n  Enter valid option: ");
			input = scanner.nextLine();
			if ( !input.equalsIgnoreCase("Q")) {
				try {
					if (Integer.parseInt(input)>=1 && Integer.parseInt(input)<=8) {
					
						String arrayAmount = fastCashAmount[Integer.parseInt(input)-1];
						validAmount = new BigDecimal(arrayAmount);
						if (atmModel.getAtmStat().equals("A")) {
							// Check the Amount - Balance
							processComplete = transaction.checkBalanceForWithdraw(validAmount, user, atmModel);
							if ( processComplete == true ) {
								
								BigDecimal currentBalanceWithInput = transaction.getCurrentBalanceWithInput();
								BigDecimal atmBalanceWithInput = transaction.getAtmBalanceWithInput();
								System.out.println("  New Balance of " + user.getCustomerID() + " : " + 
																		String.valueOf(currentBalanceWithInput));
								System.out.println("  New ATM Balance : " + String.valueOf(atmBalanceWithInput));
								inputBack = scanner.nextLine();
								
								transaction.transact("withdraw", util.convertBigDecimalString(validAmount),
													currentBalanceWithInput, atmBalanceWithInput,
													user,atmModel);
								System.out.print("  Withdraw Amount Successfully. ");
								inputBack = scanner.nextLine();
							} else {
								System.out.print("  Amount not valid for processing. Please try again. ");
								inputBack = scanner.nextLine();
							}
						} else {
							if (!(input.equalsIgnoreCase("Q"))) {
								System.out.print("  No Cash Available for Withdrawing. Please try again later ");
								inputBack = scanner.nextLine();
							}
						}
					} else {
							System.out.print("  Invalid Input. Please try again later ");
							inputBack = scanner.nextLine();
					}
				} catch (NumberFormatException e) {
					if (!(input.equalsIgnoreCase("Q"))) {
						System.out.print("  Invalid Input. Please try again later ");
						inputBack = scanner.nextLine();
					}
				}
			}
		} 
	}
	
	public static void validateStatementInput(UserModel user) throws IOException, InterruptedException {
		
		String input = new String("");
		String inputBack = new String("");
		boolean hasRecord = false;
		
		while( !(input.equalsIgnoreCase("Q")) ) {
				showScreen("Statement");
				//Column header
				System.out.println(	"  " + 
								"Customer ID" 		+ "   " +
								"Transaction Type" 	+ "   " +
								"Date and Time"		+ "             " +
								"Amount"			+ "   " );
				//Process the statement screen
				hasRecord = statement.checkRecord(user);
				if (hasRecord == false) {
					System.out.print("\n  ----------No Record found for the customer--------- \n");
				}	
				System.out.print("\n  Enter valid option: ");
				input = scanner.nextLine();
				
			if (!(input.equalsIgnoreCase("Q"))) {
				System.out.print("  Invalid Input. Please try again later ");
				inputBack = scanner.nextLine();
			}
		}
	}

	public static void validateTransferInput(UserModel user) throws IOException, InterruptedException, ParseException {
		
		String input = new String("");
		String inputBack = new String("");
		Long validAccountNo = new Long(0);
		BigDecimal validAmount = new BigDecimal("0");
		boolean isValidAccountNo = false;
		
		while( !(input.equalsIgnoreCase("Q")) ) {
			showScreen("Transfer");
			input = scanner.nextLine();
				
			if (input.equalsIgnoreCase("Q")) { continue; }	
			try {
				// Gets current balance
				AccountsModel accountsModel = new AccountsModel();
				UserLoginImplement userLogin = new UserLoginImplement();
				transactionDAO.retrieveAccountInfo(user,accountsModel);
				
				if (accountsModel.getCurrentBalance().compareTo(new BigDecimal("500"))==1) {
					
					validAccountNo = Long.parseLong(input);
					if (validAccountNo.compareTo(new Long("0")) == 1 ) {
						isValidAccountNo = transfer.checkAccountNo(user,validAccountNo);
						if ( isValidAccountNo == true ) {
							System.out.print  ("  Enter Amount to Transfer : ");
							input = scanner.nextLine();
							
							// Checking for Amount value
							validAmount = util.BigDecimalConvert(input);
							BigDecimal myBalance = accountsModel.getCurrentBalance();
							BigDecimal myBalanceCompare = myBalance.subtract(new BigDecimal("500"));
							BigDecimal updatedBalance = myBalance.subtract(validAmount);
							
							if (validAmount.compareTo(new BigDecimal("0")) == 1 && 
								myBalanceCompare.compareTo(validAmount) == 1) {
								transfer.transferAmount(user, updatedBalance);
								
								UserModel user1 = new UserModel(null, validAccountNo, "", null);
								AccountsModel accountsModel1 = new AccountsModel();
								boolean isReceiver = userLogin.validateUser(user1); // Sets the Model
								
								transactionDAO.retrieveAccountInfo(user1,accountsModel1);
								myBalance = accountsModel1.getCurrentBalance();
								updatedBalance = myBalance.add(validAmount);
								transfer.transferAmount(user1, updatedBalance);
								transfer.writeStatement(user, validAccountNo, validAmount);
								
								System.out.print("  Amount successfully transfered. ");
								inputBack = scanner.nextLine();
								
							} else {
								System.out.print("  Amount not valid for processing. ");
								inputBack = scanner.nextLine();
							} 
							
						} else {
							System.out.print("  Account Number does not exist. Please try again. ");
							inputBack = scanner.nextLine();
						}
					}
					
				} else {
					System.out.print("  Not enough amount to transfer. Please try again. ");
					inputBack = scanner.nextLine();
				}
			
			} catch (NumberFormatException e) {
				System.out.print("  Please enter a valid Account number. ");
				inputBack = scanner.nextLine();
			}
		}
	}

	public static void validateBalanceInput(UserModel user) throws IOException, InterruptedException, ParseException {
		
		String input = new String("");
		String inputBack = new String("");
		boolean hasRecord = false;
		
		while( !(input.equalsIgnoreCase("Q")) ) {
				showScreen("Balance");
				//Column header
				System.out.println("  Customer ID    Balance");
				//Process the statement screen
				hasRecord = balance.checkRecord(user);
				if (hasRecord == false) {
					System.out.print("\n  ----------No Record found for the customer--------- \n");
				} else {
					balance.transact("inquiry ", user);
				}	
				System.out.print("\n  Enter valid option: ");
				input = scanner.nextLine();
				
			if (!(input.equalsIgnoreCase("Q"))) {
				System.out.print("  Invalid Input. Please try again later ");
				inputBack = scanner.nextLine();
			}
		}
	}
	
	public static void validateTransferReport(UserModel user) throws IOException, InterruptedException {
		
		String input = new String("");
		String inputBack = new String("");
		BigDecimal validAmount = new BigDecimal(0);
		boolean hasRecord = false;
		
		while( !(input.equalsIgnoreCase("Q")) ) {
				showScreen("Transfer Report");
				//Column header
				System.out.println("  Customer    Account No   Account No      Transfer                  Amount");
				System.out.println("  ID          From         To              Date                            ");
				//Process the statement screen
				hasRecord = transReport.checkRecord(user);
				if (hasRecord == false) {
					System.out.print("\n  ----------No Record found for the customer--------- \n");
				}	
				System.out.print("\n  Enter valid option: ");
				input = scanner.nextLine();
				
			if (!(input.equalsIgnoreCase("Q"))) {
				System.out.print("  Invalid Input. Please try again later ");
				inputBack = scanner.nextLine();
			}
		}
	}


}