package biz.galaxy.translator.validator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.galaxy.translator.data.DataManager;
import com.galaxy.translator.dto.RomanSymbol;

import biz.galaxy.translator.constant.ErrorMessageConstants;

public class QuestionValidator {

	private DataManager dataManager;

	public QuestionValidator(DataManager dataManager) {
		this.dataManager = dataManager;
	}

	public String validate(String assignmnetsSequence) {

		String symbolsSequence = getSymbolsSequence(assignmnetsSequence, dataManager.getAssignmentAndSymbolMap());
		Map<String, Integer> symbolAndItsCountMap = getSymbolAndCountMap(assignmnetsSequence, dataManager.getAssignmentAndSymbolMap());
		
		String error = valiateSequenceForSymbolsNotAllowedToBeRepeated(symbolAndItsCountMap);
		error = validateFourthOccurrenceAfterSuccessiveRepeatition(symbolAndItsCountMap, symbolsSequence);
		return error;
	}

	//validation rule 1
	private String valiateSequenceForSymbolsNotAllowedToBeRepeated(Map<String, Integer> symbolAndItsCountMap) {
		for(Entry<String, Integer> entry : symbolAndItsCountMap.entrySet()) {
			String symbol = entry.getKey();
			Integer count = entry.getValue();
			if(!RomanSymbol.isSymbolRepetitionAllowed(symbol) && count > 1) {
				return ErrorMessageConstants.REPETITION_NOT_ALLOWED;
			} 
		}
		return null;
	}

	//validation rule 2 & 3
	private String validateFourthOccurrenceAfterSuccessiveRepeatition(Map<String, Integer> symbolAndItsCountMap,
			String symbolSequence) {

		Set<String> sysmbolsRepeatedMoreThanThrice = new HashSet<>();

		for(Entry<String, Integer> entry : symbolAndItsCountMap.entrySet()) {
			String symbol = entry.getKey();
			Integer count = entry.getValue();
			if(RomanSymbol.isSymbolRepetitionAllowed(symbol) && count > 3) {
				//this is the symbol which whose sequence needs to be validated.
				sysmbolsRepeatedMoreThanThrice.add(symbol);
			} 
		}

		for (String symbol : sysmbolsRepeatedMoreThanThrice) {
			String invalidSequence = symbol + symbol + symbol + symbol;
			if (symbolSequence.contains(invalidSequence)) {
				return ErrorMessageConstants.SUCCESSIVE_FOUR_TIMES_REPETITION_NOT_ALLOWED;
			}
		}

		//the symbol between third and forth occurrence must be of lesser value comparing to its own.
		// for M it is possible to fill any symbol i.e. MMM_M since all are smaller than M.
		if (sysmbolsRepeatedMoreThanThrice.contains("M")) {
			sysmbolsRepeatedMoreThanThrice.remove("M");

		}

		for(String symbol : sysmbolsRepeatedMoreThanThrice) {
			String[] invalidSequences = RomanSymbol.getInvalidSequences(symbol);
			for (String invalidSequence : invalidSequences) {
				if (symbolSequence.contains(invalidSequence)) {
					return ErrorMessageConstants.CONDITION_FOR_FOURTH_OCCURRENCE_AFTER_THREE_SUCCESSION;
				}
			}
		}
		return null;
	}

	private Map<String, Integer> getSymbolAndCountMap(final String assignmentSequenceWithSpace, 
			Map<String, String> assignementAndSymbolMap) {

		String[] assignments = assignmentSequenceWithSpace.split(" ");
		Map<String, Integer> symbolAndItsCountMap = new HashMap<>();

		for (String assignment : assignments) {
			String symbol = assignementAndSymbolMap.get(assignment);
			if(symbolAndItsCountMap.get(symbol) == null)
				symbolAndItsCountMap.put(symbol, 1);
			else {
				int value = symbolAndItsCountMap.get(symbol);
				++value;
				symbolAndItsCountMap.put(symbol, value);
			}
		}
		return symbolAndItsCountMap;
	}


	private String getSymbolsSequence(final String assignmentSequenceWithSpace, 
			Map<String, String> assignementAndSymbolMap) {

		String[] assignments = assignmentSequenceWithSpace.split(" ");
		StringBuilder stringBuilder = new StringBuilder();

		for(String assignment : assignments) {
			String symbol = assignementAndSymbolMap.get(assignment);
			stringBuilder.append(symbol);
		}
		return stringBuilder.toString();
	}
}