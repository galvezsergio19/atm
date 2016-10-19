
package com.acss.smg.utility;
/*******************************************************/
import java.text.DecimalFormat;
import java.text.ParseException;
import java.math.BigDecimal;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;
import java.util.Scanner;

public class Utility {

	public BigDecimal BigDecimalConvert( String input ) throws ParseException, PatternSyntaxException {
	
		String inputError = "";
		BigDecimal validAmount = new BigDecimal(0);
		Scanner inputBack = new Scanner(System.in);
	
		try {
			
			String reGex = "^P(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))(\\.\\d\\d?$)";
			Pattern pattern = Pattern.compile(reGex);
			Matcher matcher = pattern.matcher(input);
			
			if (matcher.find() == true) {
				
				// Create a DecimalFormat that fits your requirements
				DecimalFormat decimalFormat = new DecimalFormat("P#,###.00");
				decimalFormat.setParseBigDecimal(true);
				BigDecimal bigDecimal = (BigDecimal) decimalFormat.parse(input);
				
				// Decimal Positions parsing.
				DecimalFormat decimalformat = new DecimalFormat("#.00");
				String decimal = decimalformat.format(bigDecimal);
				BigDecimal decimalPos = new BigDecimal(decimal);
				validAmount = decimalPos;	
				
			} else {
				if (!(input.equalsIgnoreCase("Q"))) {
					System.out.print("  Invalid Amount. Please Enter correct format and try again. ");
					inputError = inputBack.nextLine();
				}
			}
			
		} catch (PatternSyntaxException ex) { 
			if (!(input.equalsIgnoreCase("Q"))) {
			System.out.print("  Invalid Amount. Please Enter correct format and try again. ");
			inputError = inputBack.nextLine();
			}
		}
		return validAmount;
	}
	
	public String convertBigDecimalString(BigDecimal bigDecimal){
		
		DecimalFormat decimalformat = new DecimalFormat("P#,###.00");
		String stringAmount = new String(decimalformat.format(bigDecimal)); 
		return stringAmount;
		
	}
	
	
}