package com.galaxy.translator.data;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataParser {
	
	private Translator translator;
	
	public DataParser(Translator translator) {
		this.translator = translator;
	} 

	private static final String ASSIGNMENT_AND_SYMBOL_REGEX = "^([a-z]+) is ([I|V|X|L|C|D|M])$";

	private static final String ASSIGNMENT_AND_METAL_REGEX = "((?:[a-z]+ )+)([A-Z]\\w+) is (\\d+) ([A-Z]\\w+)$";

	public ParsedInput parse(List<String> infoTypeInputs) {

		ParsedInput parsedInput = new ParsedInput();

		for(String input : infoTypeInputs) {
			if(input.matches(ASSIGNMENT_AND_SYMBOL_REGEX)) {
				String[] splittedInput = input.trim().split(" ");
				String assignment = splittedInput[0];
				String romanSymbol = splittedInput[2];

				parsedInput.getAssignmentAndSymbolMap().put(assignment, romanSymbol);
			} else if(input.matches(ASSIGNMENT_AND_METAL_REGEX)) {

				Matcher matcher = Pattern.compile(ASSIGNMENT_AND_METAL_REGEX).matcher(input);
				matcher.matches();

				//Credits
				parsedInput.setUnit(matcher.group(4).trim());

				//14 credits etc
				double elementAndAssignmentsTotal = Double.parseDouble(matcher.group(3).trim());

				//contains space
				String assignmentsSequence = matcher.group(1);
				
				long assignmentsTotal = getArabicNumeralFromSymbolSequence(assignmentsSequence, parsedInput.getAssignmentAndSymbolMap());
	
				parsedInput.getElementAndItsValueMap().put(matcher.group(2).trim(), getElementValue(elementAndAssignmentsTotal, assignmentsTotal));
			}
		}
		return parsedInput;
	}
	
	public long getArabicNumeralFromSymbolSequence(String assignmnetsSequence, Map<String, String> assignmentAndSymbolMap) {
		
		return translator.getArabicNumeralFromSymbols(assignmnetsSequence, assignmentAndSymbolMap);
	}
	
	public double getElementValue(double elementAndAssignmentsTotal, long assignmentsTotal) {
		return elementAndAssignmentsTotal / assignmentsTotal;
	}
}
