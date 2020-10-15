package com.capgemini.statecensustests;

import org.junit.Assert;
import org.junit.Test;


import com.capgemini.censusanalyser.CensusAnalyser;
import com.capgemini.exceptions.CensusAnalyserException;

public class CensusAnalyserTests {

	private static final String INDIA_CENSUS_CSV_FILE_PATH = "./StateCSV.csv";

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

	@Test
	public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			censusAnalyser.loadIndiaCensusData("D:\\study\\JavaOracle\\CensusAnalyser\\StateABCSV.csv");
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_FILE, e.type);
		}
	}

	@Test
	public void givenIndiaCensusData_WithWrongFileType_ShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			censusAnalyser.loadIndiaCensusData("./StateCSV.pdf");
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_FILE, e.type);
		}
	}

	@Test
	public void givenIndianCensusCSVFile_WhenWrongDelimiterProvided_ShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			censusAnalyser.loadIndiaCensusData("./StateWrongDelimiterCSV.txt");
		} catch (CensusAnalyserException e) {
			System.out.println(e.getMessage());
			Assert.assertEquals(CensusAnalyserException.ExceptionType.RUNTIME_EXCEPTION,e.type);
		}
	}
	
	@Test
	public void givenIndianCensusCSVFile_WhenWrongHeaderProvided_ShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			censusAnalyser.loadIndiaCensusData("./StateWrongHeaderCSV.txt");
		} catch (CensusAnalyserException e) {
			System.out.println(e.getMessage());
            Assert.assertEquals("Error capturing CSV header!",e.getMessage());
		}
	}

}
