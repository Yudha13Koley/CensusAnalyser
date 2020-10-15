package com.capgemini.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

import com.capgemini.exceptions.CensusAnalyserException;
import com.capgemini.indiacensuscsv.IndiaCensusCSV;
import com.capgemini.statecensusanalyser.StateCodeCSV;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CensusAnalyser {
	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
			Iterator<IndiaCensusCSV> iterator = this.getIteratorForCSVFile(reader, IndiaCensusCSV.class);
			Iterable<IndiaCensusCSV> csvIterable = () -> iterator;
			int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			return numOfEntries;
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
			Iterator<StateCodeCSV> iterator = this.getIteratorForCSVFile(reader, StateCodeCSV.class);
			Iterable<StateCodeCSV> csvIterable = () -> iterator;
			int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			return numOfEntries;
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException("No Such File Found", CensusAnalyserException.ExceptionType.WRONG_FILE);
		} catch (IOException e) {
			throw new CensusAnalyserException("IO Exception", CensusAnalyserException.ExceptionType.IO_EXCEPTION);
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.RUNTIME_EXCEPTION);
		}
	}

	private <E> Iterator<E> getIteratorForCSVFile(Reader reader, Class<E> csvClass) throws CensusAnalyserException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true).withSeparator(',');
			CsvToBean<E> csvToBean = csvToBeanBuilder.build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.PARSE_EXCEPTION);
		}

	}

}
