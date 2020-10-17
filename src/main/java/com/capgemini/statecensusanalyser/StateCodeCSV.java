package com.capgemini.statecensusanalyser;

import com.opencsv.bean.CsvBindByName;

public class StateCodeCSV {

	@CsvBindByName(column = "State Name", required = true)
	public String stateName;
	@CsvBindByName(column = "TIN", required = true)
	public String TIN;
	@CsvBindByName(column = "Population", required = true)
	public String poulation;
	@CsvBindByName(column = "State Code", required = true)
	public String stateCode;

	public StateCodeCSV() {

	}

	public StateCodeCSV(String stateName, String tIN, String poulation, String stateCode) {
		super();
		this.stateName = stateName;
		TIN = tIN;
		this.poulation = poulation;
		this.stateCode = stateCode;
	}

	@Override
	public String toString() {
		return "StateCodeCSV [stateName=" + stateName + ", TIN=" + TIN + ", poulation=" + poulation + ", stateCode="
				+ stateCode + "]";
	}

}
