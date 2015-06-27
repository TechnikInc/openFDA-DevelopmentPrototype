package gov.fda.util;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestResponse {
	@JsonProperty("RestResponse")
	private String[] messages;
	@JsonProperty("result")
	private ArrayList<CountryName2Code3Code> result;

	public String[] getMessages() {
		return messages;
	}

	public void setMessages(String[] messages) {
		this.messages = messages;
	}

	public ArrayList<CountryName2Code3Code> getResult() {
		return result;
	}

	public void setResult(ArrayList<CountryName2Code3Code> result) {
		this.result = result;
	}

}
