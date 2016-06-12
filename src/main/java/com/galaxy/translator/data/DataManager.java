package com.galaxy.translator.data;

import java.util.List;
import java.util.Map;

public class DataManager {

	private DataParser dataParser;

	private ParsedInput parsedInput;

	public DataManager(DataParser dataParser) {
		this.dataParser = dataParser;
	}

	public void extractInfo(List<String> infoTypeInputs) {
		this.parsedInput = dataParser.parse(infoTypeInputs);
	}
	
	public String getUnit() {
		return parsedInput.getUnit();
	}

	public Double getElementValue(String elementName) {
		return parsedInput.getElementAndItsValueMap().get(elementName);
	}

	public Map<String, String> getAssignmentAndSymbolMap() {
		return parsedInput.getAssignmentAndSymbolMap();
	}
}
