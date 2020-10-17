package com.capgemini.statecensus;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import com.capgemini.censusanalyser.CensusAnalyser;
import com.capgemini.exceptions.CensusAnalyserException;
import com.capgemini.indiacensuscsv.IndiaCensusCSV;
import com.capgemini.statecensusanalyser.StateCodeCSV;
import com.google.gson.Gson;

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

	@Test
	public void givenIndianStateCensusCSVFile_WithWrongFile_ShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			censusAnalyser.loadStateCode("./IndianStateCensusWrongData.csv");
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_FILE, e.type);
		}
	}

	@Test
	public void givenIndianStateCensusCSVFile_WithWrongFileType_ShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			censusAnalyser.loadStateCode("./IndianStateCensusData.xlsx");
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_FILE, e.type);
		}
	}

	/*
	 * @Test public void
	 * givenIndianStateCensusCSVFile_WhenWrongDelimiterProvided_ShouldThrowException
	 * () { try { CensusAnalyser censusAnalyser = new CensusAnalyser();
	 * censusAnalyser.loadStateCode("./IndianStateCensusDataWrongDelimiter.txt"); }
	 * catch (CensusAnalyserException e) { System.out.println(e.getMessage());
	 * Assert.assertEquals(CensusAnalyserException.ExceptionType.RUNTIME_EXCEPTION,
	 * e.type); } }
	 */

	@Test
	public void givenIndianStateCensusCSVFile_WhenWrongHeaderProvided_ShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			censusAnalyser.loadStateCode("./IndianStateCensusDataWrongHeader.txt");
		} catch (CensusAnalyserException e) {
			System.out.println(e.getMessage());
			Assert.assertEquals(CensusAnalyserException.ExceptionType.RUNTIME_EXCEPTION, e.type);
		}
	}

	@Test
	public void givenIndianCensusCSVFile_WhenSortedByState_ShouldReturnSortedList() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			String sortedlist = censusAnalyser.getSortedlistByStateCode(STATE_FILE_PATH);
			StateCodeCSV[] arr = new Gson().fromJson(sortedlist, StateCodeCSV[].class);
			Assert.assertEquals("AN", arr[0].stateCode);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
	}

}
