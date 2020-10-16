package com.capgemini.interfaces;

import java.io.Reader;
import java.util.Iterator;

import com.capgemini.exceptions.CensusAnalyserException;

public interface ICSVBuilder<E>{
	public Iterator<E> getIteratorForCSVFile(Reader reader, Class<E> csvClass) throws CensusAnalyserException;
}
