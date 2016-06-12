package com.galaxy.translator.dto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RomanSymbol {

	private final static Map<String, Integer> symbolTypes = new HashMap<String, Integer>();

	/**
	 * The symbols not allowed to be subtracted as per the rule.
	 */
	private static Set<String> symbolsNotSubtractable;

	/**
	 * The symbols not allowed to be subtracted as per the rule.
	 */
	private static Set<String> symbolsNotAllowedToBeRepeated;

	/**
	 * The combination of symbols allowed to be subtracted as per the rule.
	 */
	private static Map<String, String[]> symbolAndisSubtractableFromSymbols;
	
	/**
	 * This map holds the symbols sequence that are unacceptable.
	 */
	private final static Map<String, String[]> invalidRepetitionsMap = new HashMap<>();

	static {
		symbolTypes.put("I", Integer.valueOf(1));
		symbolTypes.put("V", Integer.valueOf(5));
		symbolTypes.put("X", Integer.valueOf(10));
		symbolTypes.put("L", Integer.valueOf(50));
		symbolTypes.put("C", Integer.valueOf(100));
		symbolTypes.put("D", Integer.valueOf(500));
		symbolTypes.put("M", Integer.valueOf(1000));

		//rule4.
		//V L D can never be subtracted. also M
		//I , X , C are allowed to be subtracted from next two.
		//e.g. I can be subtracted from X, C
		symbolsNotSubtractable = new HashSet<>();
		symbolsNotSubtractable.add("V");
		symbolsNotSubtractable.add("L");
		symbolsNotSubtractable.add("D");
		symbolsNotSubtractable.add("M");

		symbolAndisSubtractableFromSymbols = new HashMap<>();
		symbolAndisSubtractableFromSymbols.put("I", new String[]{"V", "X"});
		symbolAndisSubtractableFromSymbols.put("X", new String[]{"L", "C"});
		symbolAndisSubtractableFromSymbols.put("C", new String[]{"D", "M"});

		symbolsNotAllowedToBeRepeated = new HashSet<>();
		symbolsNotAllowedToBeRepeated.add("V");
		symbolsNotAllowedToBeRepeated.add("L");
		symbolsNotAllowedToBeRepeated.add("D");
		
		invalidRepetitionsMap.put("I", new String[]{"IIIVI", "IIIXI", "IIILI", "IIICI", "IIIDI", "IIIMI"});
		invalidRepetitionsMap.put("X", new String[]{"XXXLX", "XXXCX", "XXXDX", "XXXMX"});
		invalidRepetitionsMap.put("C", new String[]{"CCCDC", "CCCMC"});
	}

	public static Integer getSymbolValue(String symbol) {
		return symbolTypes.get(symbol);
	}


	public static boolean isSymbolNotSubtractable(String symbol) {
		return symbolsNotSubtractable.contains(symbol);
	}

	public static String[] subtractableFrom(String symbol) {
		return symbolAndisSubtractableFromSymbols.get(symbol);
	}

	public static boolean isSymbolRepetitionAllowed(String symbol) {
		return !symbolsNotAllowedToBeRepeated.contains(symbol);
	}

	public static String[] getInvalidSequences(String symbol) {
		return invalidRepetitionsMap.get(symbol);
	}

}
