
package com.acss.smg.dao;
import com.acss.smg.model.AtmStatusModel;
/*******************************************/
import java.io.IOException;
import java.text.ParseException;

public interface AtmStatusDAO{
	public void retrieveAtmInfo(AtmStatusModel atmModel) throws IOException, ParseException;
}