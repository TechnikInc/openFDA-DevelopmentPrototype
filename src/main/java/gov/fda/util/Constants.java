package gov.fda.util;

import java.util.List;

import org.springframework.web.client.RestTemplate;

public class Constants {
	
	public static final String COUNTRY_FLAG_URL ="http://www.geonames.org/flags/x/";
	public static final String FLAG_FILE_EXTENSION = ".gif";
	public static final String FDA_BASE_URL= "https://api.fda.gov/drug/event.json?";
	public static final String RECEIVED_DATE_PREDICATE= "receivedate:[20040101+TO+20150101]";
	public static final String AND_OPERATION = "+AND+";
	public static final String DESEASE_AND_NUM_INCIDENTS_QUERY = "patient.drug.drugindication:hypertension&count=patient.drug.drugindication";
	
	public static final String COUNTRY_AND_NUM_INCIDENTS_QUERY = "&count=occurcountry";
	
	public static final String COUNTRY_LATEST_INCIDENTS_QUERY = "search=occurcountry:";
	
	public static final String DRUG_AND_NUM_INCIDENTS_QUERY= "count=patient.drug.medicinalproduct";
	
	public static final String LIMIT_PREDICATE = "&limit=";
	public static final String SKIP_PREDICATE = "&skip=";
	public static final String SEARCH_PREDICATE = "&search=";
	
	public static final String DRUG_SEARCH = "patient.drug.medicinalproduct:";
	

}
