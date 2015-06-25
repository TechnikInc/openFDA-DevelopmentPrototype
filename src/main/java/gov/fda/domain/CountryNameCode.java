package gov.fda.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryNameCode {
	 private String name;
	 private String code;
	 
	 public CountryNameCode(String name, String code){
		 this.name = name;
		 this.code = code;
	 }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}	   
}
