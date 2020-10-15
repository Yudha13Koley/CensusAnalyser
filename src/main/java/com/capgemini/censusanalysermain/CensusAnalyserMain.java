package com.capgemini.censusanalysermain;

import com.capgemini.censusanalyser.CensusAnalyser;

public class CensusAnalyserMain {
public static void main(String[] args) {
	System.out.println("Welcome to Census Analyser Problem !");
	CensusAnalyser ca=new CensusAnalyser();
	int a=ca.loadIndiaCensusData("./StateCSV.csv");
	System.out.println("No of Entries : "+a);
}
}
