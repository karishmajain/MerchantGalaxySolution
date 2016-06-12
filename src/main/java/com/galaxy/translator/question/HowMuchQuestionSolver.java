package com.galaxy.translator.question;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.galaxy.translator.data.DataManager;
import com.galaxy.translator.data.Translator;
import com.galaxy.translator.dto.QuestionOutput;

import biz.galaxy.translator.validator.QuestionValidator;

public class HowMuchQuestionSolver implements QuestionSolver {

	private static final String HOW_MUCH_REGEX = "^how much is ((?:\\w+[^0-9] )+)\\?$";

	@Override
	public QuestionOutput solve(QuestionValidator questionValidator, Translator translator, DataManager dataManager, String question) {

		String answer = null;

		Matcher matcher = Pattern.compile(HOW_MUCH_REGEX).matcher(question);
		matcher.matches();

		String assignmentsSequence = matcher.group(1).trim();

		String validationResult = questionValidator.validate(assignmentsSequence);
		if(validationResult != null) {
			return new QuestionOutput(answer, validationResult);
		}

		long totalArabicNumeralValue = translator.getArabicNumeralFromSymbols(assignmentsSequence, dataManager.getAssignmentAndSymbolMap());
		answer =  decorateOutput(assignmentsSequence, totalArabicNumeralValue);

		return new QuestionOutput(answer, null);
	}

	private String decorateOutput(String assignmentsSequence, long totalArabicNumeralValue) {
		return assignmentsSequence + " is " + totalArabicNumeralValue;
	}
}
