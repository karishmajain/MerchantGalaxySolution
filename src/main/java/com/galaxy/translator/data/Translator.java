package com.galaxy.translator.data;

import java.util.Map;

import com.galaxy.translator.dto.RomanSymbol;

public class Translator {

	/**
	 * This method calculates the numeral value from the given assignments. A
	 * rule is applied that when smaller values precede larger values, the
	 * smaller values are subtracted from the larger values and result is added
	 * to total.
	 *
	 * @param assignments The assignments from the given input.
	 * @return The numeral value.
	 */
	public long getArabicNumeralFromSymbols(String assignmentsSequence, Map<String, String> assignmentAndSymbolMap) {
		String[] assignments = assignmentsSequence.split(" ");
		
		long result = 0;
		boolean matchFound;
		for (int i = 0; i < assignments.length; i++) {
			matchFound = false;
			Integer arabicNumeral = RomanSymbol.getSymbolValue(assignmentAndSymbolMap.get(assignments[i]));
			if (RomanSymbol.isSymbolNotSubtractable(assignmentAndSymbolMap.get(assignments[i]))) {
				//direct add;
				result += arabicNumeral;
			} else {
				String[] matches = RomanSymbol.subtractableFrom(assignmentAndSymbolMap.get(assignments[i]));
				if (assignments.length != i + 1) {
					String toMatch = assignmentAndSymbolMap.get(assignments[i + 1]);
					for (String match : matches) {
						if (match.equals(toMatch)) {
							matchFound = true;
							break;
						}
					}
					if (matchFound) {
						result -= arabicNumeral;
					} else {
						result += arabicNumeral;
					}
				} else {
					result += arabicNumeral;
				}
			}
		}
		return result;
	}
}
