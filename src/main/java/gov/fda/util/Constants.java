package gov.fda.util;

import java.util.List;

import org.springframework.web.client.RestTemplate;

public class Constants {
	
	public static final String COUNTRY_FLAG_URL ="http://www.geonames.org/flags/x/";
	public static final String FLAG_FILE_EXTENSION = ".gif";
	public static final String FDA_BASE_URL= "https://api.fda.gov/drug/event.json?";
	public static final String SEARCH_PREDICATE = "search";
	public static final String COUNT_PREDICATE = "count";
	public static final String OCCUR_COUNTRY = "occurcountry";
	public static final String PREDICATE_SEPARATION = ":";

	public static final String AND_OPERATION = "+AND+";
	
	public static final String DRUG_SEARCH = "patient.drug.medicinalproduct";
	

}
