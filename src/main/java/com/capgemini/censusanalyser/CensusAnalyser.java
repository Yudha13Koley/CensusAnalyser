package com.capgemini.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.capgemini.indiacensuscsv.IndiaCensusCSV;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CensusAnalyser {
	 public int loadIndiaCensusData(String csvFilePath) {
	        try {
	        	Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
	            CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
	            csvToBeanBuilder.withType(IndiaCensusCSV.class);
	            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
	            CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
	            Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();;
	            int noOfRecords = 0;
	            while (censusCSVIterator.hasNext()) {
	                noOfRecords++;
	                IndiaCensusCSV censusData = censusCSVIterator.next();
	                System.out.println(censusData);
	            }
	            return noOfRecords;
	        } catch (IOException e) {
	           e.printStackTrace();
	           return 0;
	        } 
	    }
}
