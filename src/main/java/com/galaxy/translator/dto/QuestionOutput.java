package com.galaxy.translator.dto;

public class QuestionOutput {
	
	private String answer;
	
	private String error;
	
	public QuestionOutput(String answer, String error) {
		this.answer = answer;
		this.error = error;
	}
	
	public boolean isPositive() {
		return answer != null;
	}

	public String getAnswer() {
		return answer;
	}

	public String getError() {
		return error;
	}

}
