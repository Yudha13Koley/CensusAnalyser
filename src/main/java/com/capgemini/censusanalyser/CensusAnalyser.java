package com.capgemini.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

import com.capgemini.exception.CSVBuilderException;
import com.capgemini.exceptions.CensusAnalyserException;
import com.capgemini.factory.CSVBuilderFactory;
import com.capgemini.indiacensuscsv.IndiaCensusCSV;
import com.capgemini.interfaces.ICSVBuilder;
import com.capgemini.statecensusanalyser.StateCodeCSV;

public class CensusAnalyser {
	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
			ICSVBuilder csvBuilder = CSVBuilderFactory.getCSVBuilder();
			List<IndiaCensusCSV> listOfEntries = csvBuilder.getListForCSVFile(reader, IndiaCensusCSV.class);
			return listOfEntries.size();
		} catch (CSVBuilderException e) {
			throw new CensusAnalyserException("Unable to Parse", CensusAnalyserException.ExceptionType.PARSE_EXCEPTION);
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException("No Such File Found", CensusAnalyserException.ExceptionType.WRONG_FILE);
		} catch (IOException e) {
			throw new CensusAnalyserException("IO Exception", CensusAnalyserException.ExceptionType.IO_EXCEPTION);
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.RUNTIME_EXCEPTION);
		}
	}

	public int loadStateCode(String csvFilePath) throws CensusAnalyserException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
			ICSVBuilder csvBuilder = CSVBuilderFactory.getCSVBuilder();
			List<StateCodeCSV> listOfStateEntries = csvBuilder.getListForCSVFile(reader, StateCodeCSV.class);
			return listOfStateEntries.size();
		} catch (CSVBuilderException e) {
			throw new CensusAnalyserException("Unable to Parse", CensusAnalyserException.ExceptionType.PARSE_EXCEPTION);
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException("No Such File Found", CensusAnalyserException.ExceptionType.WRONG_FILE);
		} catch (IOException e) {
			throw new CensusAnalyserException("IO Exception", CensusAnalyserException.ExceptionType.IO_EXCEPTION);
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.RUNTIME_EXCEPTION);
		}
	}

	private <E> int getCountOfEntries(Iterator<E> iterator) {
		Iterable<E> csvIterable = () -> iterator;
		return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
	}

}
