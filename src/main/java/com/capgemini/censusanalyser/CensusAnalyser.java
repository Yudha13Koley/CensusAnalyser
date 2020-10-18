package com.capgemini.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import com.capgemini.exception.CSVBuilderException;
import com.capgemini.exceptions.CensusAnalyserException;
import com.capgemini.factory.CSVBuilderFactory;
import com.capgemini.indiacensuscsv.IndiaCensusCSV;
import com.capgemini.interfaces.ICSVBuilder;
import com.capgemini.statecensusanalyser.StateCodeCSV;
import com.google.gson.Gson;

public class CensusAnalyser {

	private <E> List<E> getListOfData(String csvFilePath, Class<E> objectclass) throws CensusAnalyserException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
			ICSVBuilder csvBuilder = CSVBuilderFactory.getCSVBuilder();
			return csvBuilder.getListForCSVFile(reader, objectclass);
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

	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
		List<IndiaCensusCSV> listOfEntries = this.getListOfData(csvFilePath, IndiaCensusCSV.class);
		return listOfEntries.size();
	}

	public int loadStateCode(String csvFilePath) throws CensusAnalyserException {
		List<StateCodeCSV> listOfEntries = this.getListOfData(csvFilePath, StateCodeCSV.class);
		return listOfEntries.size();
	}

	/*
	 * private <E> int getCountOfEntries(Iterator<E> iterator) { Iterable<E>
	 * csvIterable = () -> iterator; return (int)
	 * StreamSupport.stream(csvIterable.spliterator(), false).count(); }
	 */

	public String getSortedlistByState(String csvFilePath) throws CensusAnalyserException {
		List<IndiaCensusCSV> listOfEntries = this.getListOfData(csvFilePath, IndiaCensusCSV.class);
		Comparator<IndiaCensusCSV> comparator = Comparator.comparing(indiaCensus -> indiaCensus.state);
		List<IndiaCensusCSV> sortedListOfEntries = this.sortList(listOfEntries, comparator);
		String json = new Gson().toJson(sortedListOfEntries);
		return json;
	}

	public String getSortedlistByStateCode(String stateFilePath) throws CensusAnalyserException {
		List<StateCodeCSV> listOfStateEntries = this.getListOfData(stateFilePath, StateCodeCSV.class);
		Comparator<StateCodeCSV> comparator = Comparator.comparing(stateCensus -> stateCensus.stateCode);
		List<StateCodeCSV> sortedListOfEntries = this.sortList(listOfStateEntries, comparator);
		String json = new Gson().toJson(sortedListOfEntries);
		return json;
	}

	public String getSortedlistByPopulation(String csvFilePath) throws CensusAnalyserException {
		List<IndiaCensusCSV> listOfEntries = this.getListOfData(csvFilePath, IndiaCensusCSV.class);
		Comparator<IndiaCensusCSV> comparator = Comparator.comparing(indiaCensus -> indiaCensus.population);
		List<IndiaCensusCSV> sortedListOfEntries = this.sortList(listOfEntries, comparator);
		Collections.reverse(sortedListOfEntries);
		String json = new Gson().toJson(sortedListOfEntries);
		return json;
	}

	public String getSortedlistByPopulationDensity(String csvFilePath) throws CensusAnalyserException {
		List<IndiaCensusCSV> listOfEntries = this.getListOfData(csvFilePath, IndiaCensusCSV.class);
		Comparator<IndiaCensusCSV> comparator = Comparator.comparing(indiaCensus -> indiaCensus.densityPerSqKm);
		List<IndiaCensusCSV> sortedListOfEntries = this.sortList(listOfEntries, comparator);
		Collections.reverse(sortedListOfEntries);
		String json = new Gson().toJson(sortedListOfEntries);
		return json;
	}

	public String getSortedlistByStateArea(String csvFilePath) throws CensusAnalyserException {
		List<IndiaCensusCSV> listOfEntries = this.getListOfData(csvFilePath, IndiaCensusCSV.class);
		Comparator<IndiaCensusCSV> comparator = Comparator.comparing(indiaCensus -> indiaCensus.areaInSqKm);
		List<IndiaCensusCSV> sortedListOfEntries = this.sortList(listOfEntries, comparator);
		Collections.reverse(sortedListOfEntries);
		String json = new Gson().toJson(sortedListOfEntries);
		return json;
	}

	private <E> List<E> sortList(List<E> listOfEntries, Comparator<E> comparator) {
		return listOfEntries.stream().sorted(comparator).collect(Collectors.toList());

	}
}
