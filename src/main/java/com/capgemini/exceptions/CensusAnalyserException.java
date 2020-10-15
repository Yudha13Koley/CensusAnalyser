package com.capgemini.exceptions;

public class CensusAnalyserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExceptionType type;

	public CensusAnalyserException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}

	public enum ExceptionType {
		IO_EXCEPTION, WRONG_FILE, RUNTIME_EXCEPTION
	}

}
