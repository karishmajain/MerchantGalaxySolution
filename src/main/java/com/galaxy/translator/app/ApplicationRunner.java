package com.galaxy.translator.app;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.galaxy.translator.data.DataManager;
import com.galaxy.translator.data.DataParser;
import com.galaxy.translator.data.Translator;
import com.galaxy.translator.dto.QuestionOutput;
import com.galaxy.translator.question.QuestionManager;

import biz.galaxy.translator.validator.QuestionValidator;

public class ApplicationRunner {

	public static void main(String args[]) throws IOException, URISyntaxException {

		List<String> inputs = Files.readAllLines(Paths.get(ClassLoader.getSystemResource("input.txt").toURI()), Charset.forName("UTF-8"));
		List<String> infoTypeInputs = new ArrayList<String>();
		List<String> questionTypeInputs = new ArrayList<String>();
		
		for(String input : inputs) {
			if(isInputOfQuestionType(input)) {
				questionTypeInputs.add(input);
			} else {
				infoTypeInputs.add(input);
			}
		}

		Translator translator = new Translator();
		DataManager dataManager = new DataManager(new DataParser(translator));
		dataManager.extractInfo(infoTypeInputs);

		QuestionValidator questionValidator = new QuestionValidator(dataManager);
		
		QuestionManager questionManager = new QuestionManager(dataManager, questionValidator, translator);
		
		for(String question : questionTypeInputs) {
			QuestionOutput output = questionManager.solve(question);
			if(output.isPositive()) {
				System.out.println(output.getAnswer());
			} else {
				System.out.println(output.getError());
			}
			
		}
	}

	private static boolean isInputOfQuestionType(String input) {
		return input.contains("how");
	}

	//	private static boolean isInputOfInformationType(String input) {
	//		return !input.contains("how");
	//	}

}
