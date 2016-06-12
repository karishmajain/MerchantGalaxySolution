package com.galaxy.translator.question;

public class QuestionSolverFactory {

	private static final String HOW_MANY_REGEX = "^how many ([a-zA-Z]\\w+) is ((?:\\w+ )+)([A-Z]\\w+) \\?$";

	private static final String HOW_MUCH_REGEX = "^how much is ((?:\\w+[^0-9] )+)\\?$";

	public static QuestionSolver getQuestionSolver(String question) {

		if(question.matches(HOW_MUCH_REGEX)) {
			return new HowMuchQuestionSolver();
		} else if(question.matches(HOW_MANY_REGEX)) {
			return new HowManyQuestionSolver();
		}
		return null;
	}
}
