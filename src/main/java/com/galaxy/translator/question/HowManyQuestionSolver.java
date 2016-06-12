package com.galaxy.translator.question;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.galaxy.translator.data.DataManager;
import com.galaxy.translator.data.Translator;
import com.galaxy.translator.dto.QuestionOutput;

import biz.galaxy.translator.constant.ErrorMessageConstants;
import biz.galaxy.translator.validator.QuestionValidator;

public class HowManyQuestionSolver implements QuestionSolver {

	private static final String HOW_MANY_REGEX = "^how many ([a-zA-Z]\\w+) is ((?:\\w+ )+)([A-Z]\\w+) \\?$";
	
	@Override
	public QuestionOutput solve(QuestionValidator questionValidator, Translator translator, DataManager dataManager, String question) {
		
		String answer = null, error = null;
		
		Matcher matcher = Pattern.compile(HOW_MANY_REGEX).matcher(question);
		matcher.matches();
		
		  if(!dataManager.getUnit().equals(matcher.group(1).trim())) {
			  return new QuestionOutput(answer, ErrorMessageConstants.UNIT_NON_EXISTENCE);
	        }
        
        String elementName = matcher.group(3).trim();
        Double elementValue = dataManager.getElementValue(elementName);
        if (elementValue == null) {
        	return new QuestionOutput(answer, ErrorMessageConstants.ELEMENT_NON_EXISTENCE);
        }
        
        String assignmentsSequence = matcher.group(2).trim();
        String validationResult = questionValidator.validate(assignmentsSequence);
        if(null != validationResult) {
        	return new QuestionOutput(answer, validationResult);
        	
        }
        
        long assignmentsTotal = translator.getArabicNumeralFromSymbols(assignmentsSequence, dataManager.getAssignmentAndSymbolMap());
        
        double total = assignmentsTotal * elementValue;
        
        answer = assignmentsSequence + " " + elementName + " is " + (long) total + " " + dataManager.getUnit();
        
        return new QuestionOutput(answer, error);
	}

}
