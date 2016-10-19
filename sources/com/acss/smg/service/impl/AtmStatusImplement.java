
package com.acss.smg.service.impl;
import com.acss.smg.constant.Constant;
import com.acss.smg.dao.impl.AtmStatusDAOImplement;
import com.acss.smg.model.AtmStatusModel;
import com.acss.smg.service.AtmStatusService;
/*****************************************************/
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;

public class AtmStatusImplement implements AtmStatusService {

	// Constructor
	private AtmStatusDAOImplement atm = null;
	
	public boolean checkStatus(AtmStatusModel atmModel) throws IOException, ParseException {
	
		boolean isAtmAvailable = false;
		try {
			atm = new AtmStatusDAOImplement();
			atm.retrieveAtmInfo(atmModel);
			if ( atmModel.getAtmStat().equals("A") || atmModel.getAtmStat().equals("NC") ) {
				isAtmAvailable = true;
			}
		} catch (NullPointerException e) {
			System.out.println("Error: Null pointer.");
		}
		return isAtmAvailable;
	}
	
	public BigDecimal getAtmCurrentAmount(AtmStatusModel atmModel) throws IOException, ParseException {
		
		try {
			atm = new AtmStatusDAOImplement();
			atm.retrieveAtmInfo(atmModel);
		} catch (NullPointerException e) {
			System.out.println("Error: Null pointer.");
		}
		return atmModel.getCurrentAmount();
	}
	
}