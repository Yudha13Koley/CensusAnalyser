package com.capgemini.statecensusanalyser;

import com.opencsv.bean.CsvBindByName;

public class StateCodeCSV {

	@CsvBindByName(column = "State Name", required = true)
	public String stateName;

	@CsvBindByName
	public String stateCode;
}
