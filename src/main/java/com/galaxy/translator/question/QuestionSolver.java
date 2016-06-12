package com.galaxy.translator.question;

import com.galaxy.translator.data.DataManager;
import com.galaxy.translator.data.Translator;
import com.galaxy.translator.dto.QuestionOutput;

import biz.galaxy.translator.validator.QuestionValidator;

public interface QuestionSolver {
	
	public QuestionOutput solve(QuestionValidator questionValidator, Translator translator, DataManager dataManager,
			String question);
}
