package com.galaxy.translator.question;

import com.galaxy.translator.data.DataManager;
import com.galaxy.translator.data.Translator;
import com.galaxy.translator.dto.QuestionOutput;

import biz.galaxy.translator.validator.QuestionValidator;

public class QuestionManager {

	private DataManager dataManager;

	private QuestionValidator questionValidator;
	
	private Translator translator;

	public QuestionManager(DataManager dataManager, QuestionValidator questionValidator, Translator translator) {
		this.dataManager = dataManager;
		this.questionValidator = questionValidator;
		this.translator = translator;
	}

	public QuestionOutput solve(String question) {
		if(null != QuestionSolverFactory.getQuestionSolver(question)) {
			return QuestionSolverFactory.getQuestionSolver(question).solve(questionValidator, translator, dataManager, question);
		}

		return new QuestionOutput(null, "I have no idea what you are talking about");
	} 
}
