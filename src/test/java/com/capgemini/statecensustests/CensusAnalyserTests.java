package com.capgemini.statecensustests;




import org.junit.Assert;
import org.junit.Test;

import com.capgemini.censusanalyser.CensusAnalyser;

public class CensusAnalyserTests {

	private static final String INDIA_CENSUS_CSV_FILE_PATH = "./StateCSV.csv";
	//private static final String INDIA_STATE_CENSUS_CSV_FILE_PATH = "./IndianStateCensusData.csv";

	@Test
	 public void givenIndianCensusCSVFile_ReturnsNumberOfRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}

}
