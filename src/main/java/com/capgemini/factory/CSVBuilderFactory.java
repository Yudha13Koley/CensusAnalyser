package com.capgemini.factory;

import com.capgemini.interfaces.ICSVBuilder;
import com.capgemini.opencsvbuilder.OpenCSVBuilder;

public class CSVBuilderFactory<E> {

	public static ICSVBuilder getCSVBuilder() {
		return new OpenCSVBuilder();
	}
}
