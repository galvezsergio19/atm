
package com.acss.smg.service;
import com.acss.smg.model.AtmStatusModel;
/*****************************************************/
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;

public interface AtmStatusService {
	public boolean checkStatus(AtmStatusModel atmModel) throws IOException, ParseException ;
	public BigDecimal getAtmCurrentAmount(AtmStatusModel atmModel) throws IOException, ParseException;
}