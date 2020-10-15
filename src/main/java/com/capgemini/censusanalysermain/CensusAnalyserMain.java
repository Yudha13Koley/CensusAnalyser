package com.capgemini.censusanalysermain;

import com.capgemini.censusanalyser.CensusAnalyser;
import com.capgemini.exceptions.CensusAnalyserException;

public class CensusAnalyserMain {
	public static void main(String[] args) {
		System.out.println("Welcome to Census Analyser Problem !");
		CensusAnalyser ca = new CensusAnalyser();
		int a;
		try {
			a = ca.loadIndiaCensusData("./StateCSV.csv");
			System.out.println("No of Entries : " + a);
		} catch (CensusAnalyserException e) {
			e.getMessage();
		}
	}
}
