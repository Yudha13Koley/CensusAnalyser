package com.capgemini.statecensus;

import org.junit.Assert;
import org.junit.Test;

import com.capgemini.censusanalyser.CensusAnalyser;

public class StateCensusTests {

	private static final String STATE_FILE_PATH = "./IndianStateCensusData.csv";

	@Test
	public void givenIndianStateCensusCSVFile_ReturnsCorrectRecords() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			int numOfRecords = censusAnalyser.loadStateCode(STATE_FILE_PATH);
			Assert.assertEquals(36, numOfRecords);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
