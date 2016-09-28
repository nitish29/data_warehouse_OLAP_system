package com.ub.cse601.ui;

import java.util.List;

public class OLAPUtilities {

	public static Object[][] convertListToArray(List<String[]> results) {
		Object[][] objectArray = new Object[results.size()][];
		for (int i = 0; i < results.size(); i++) {
			objectArray[i] = results.get(i);

		}
		return objectArray;

	}
}
