
package com.acss.smg.model;
import java.math.BigDecimal;
public class AtmStatusModel {

	/**************************************************************************/
	//  Variables
	/**************************************************************************/
	private BigDecimal currentAmount;
	private String atmStat;
	
	/**************************************************************************/
	//  Constructors
	/**************************************************************************/
	public AtmStatusModel() {}
	public AtmStatusModel(BigDecimal currentAmount, String atmStat) {
		this.currentAmount = currentAmount;
		this.atmStat = atmStat;
	}
	
	/**************************************************************************/
	//  Setter
	/**************************************************************************/
	public void setCurrentAmount(BigDecimal currentAmount){
		this.currentAmount = currentAmount;
	}
	
	public void setAtmStat(String atmStat) {
		this.atmStat = atmStat;
	}
	
	/**************************************************************************/
	//  Getter
	/**************************************************************************/
	public BigDecimal getCurrentAmount() {
		return currentAmount;
	}
	
	public String getAtmStat() {
		return atmStat;
	}

}