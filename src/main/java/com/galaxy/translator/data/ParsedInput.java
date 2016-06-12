package com.galaxy.translator.data;

import java.util.HashMap;
import java.util.Map;

public class ParsedInput {

	private String unit;

	private Map<String, String> assignmentAndSymbolMap = new HashMap<>();

	private Map<String, Double> elementAndItsValueMap  = new HashMap<>();

	public String getUnit() {
		return unit;
	}

	void setUnit(String unit) {
		this.unit = unit;
	}

	public Map<String, String> getAssignmentAndSymbolMap() {
		return assignmentAndSymbolMap;
	}

	void setAssignmentAndSymbolMap(Map<String, String> assignmentAndSymbolMap) {
		this.assignmentAndSymbolMap = assignmentAndSymbolMap;
	}

	public Map<String, Double> getElementAndItsValueMap() {
		return elementAndItsValueMap;
	}

	void setElementAndItsValueMap(Map<String, Double> elementAndItsValueMap) {
		this.elementAndItsValueMap = elementAndItsValueMap;
	}
}
