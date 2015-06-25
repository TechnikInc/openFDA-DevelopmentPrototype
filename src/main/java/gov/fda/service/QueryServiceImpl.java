package gov.fda.service;

import gov.fda.domain.CountryAndIncidents;
import gov.fda.domain.CountryNameCode;
import gov.fda.domain.CountryNameCodeList;
import gov.fda.domain.CountryResult;
import gov.fda.domain.NumIncidents;
import gov.fda.domain.Result;
import gov.fda.util.CountryNameCodeRefresher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QueryServiceImpl implements QueryService{
	
	protected  RestTemplate restTemplate;
	public static final String COUNTRY_FLAG_URL ="http://www.geonames.org/flags/x/";
	public static final String FLAG_FILE_EXTENSION = ".gif";
	protected static final String FDA_BASE_URL= "https://api.fda.gov/drug/event.json?";
	protected static final String RECEIVED_DATE_PREDICATE= "receivedate:[20040101+TO+20150101]";
	protected static final String AND_OPERATION = "+AND+";
	protected static final String DESEASE_AND_NUM_INCIDENTS_QUERY = "patient.drug.drugindication:hypertension&count=patient.drug.drugindication";
	
	protected static final String COUNTRY_AND_NUM_INCIDENTS_QUERY = "count=occurcountry";
	
	protected static final String COUNTRY_LATEST_INCIDENTS_QUERY = "search=occurcountry:";
	
	protected static final String DRUG_AND_NUM_INCIDENTS_QUERY= "count=patient.drug.medicinalproduct";
	
	protected static final String LIMIT_PREDICATE = "&limit=";
	protected static final String SKIP_PREDICATE = "&skip=";
	
	protected List<CountryNameCode> countries;
	
	static{
		// FDA data does not have unique country codes and names as per API.
		// so we query the country code names from http://data.okfn.org/data/core/country-list/r/data.json
		// these will be loaded in  

	}
	
	
	private static final Logger logger = LoggerFactory.getLogger(QueryServiceImpl.class);
	
	private void loadCountriesStaticData()
	{
		if(countries == null)
		   countries = CountryNameCodeRefresher.getCountries();
	}
	
	public QueryServiceImpl()
	{
		this.restTemplate = new RestTemplate();
	}
	
	public List<Result> getNumberOfInsidentsByDeseae()
	{
		String queryString =
				"https://api.fda.gov/drug/event.json?search={receivedate:[{20040101}+TO+{20150101}]}";
		logger.debug(queryString);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("search", "receivedate:[20040101+TO+20150101]");
		params.put("count", "receivedate");
		
		NumIncidents wrapper = restTemplate.getForObject(queryString, NumIncidents.class);
		return sortResults(wrapper.getResults());
	}
	
	private List<Result> sortResults(List<Result> resultList)
	{
		NumIncidents.sortResults(resultList);
		return resultList;
	}
	
	public List<CountryResult> getNumberOfIncidentsByCounty(){
		this.restTemplate = new RestTemplate();
		String queryString =
				FDA_BASE_URL+COUNTRY_AND_NUM_INCIDENTS_QUERY;
		logger.debug(queryString);
		CountryAndIncidents wrapper = restTemplate.getForObject(queryString, CountryAndIncidents.class);
		List<CountryResult> countiresFDA =wrapper.getResults();
		CountryAndIncidents.sortList(wrapper.getResults());
		loadCountriesStaticData();
		for(CountryResult country : countiresFDA){
			country.setCountryName(
					CountryAndIncidents.getCountryName(country.getTerm(), countries));
			country.setImageSrc(getFlagSource(country.getTerm()));
		}
		return countiresFDA;		
	}
	
	private String getFlagSource(String countryShortName)
	{
		return COUNTRY_FLAG_URL+countryShortName+FLAG_FILE_EXTENSION;
	}
	
	
	public List<Result> getNumberOfIncidentsByDrug(){
		this.restTemplate = new RestTemplate();
		String queryString =
				FDA_BASE_URL+DRUG_AND_NUM_INCIDENTS_QUERY;
		logger.debug(queryString);
		NumIncidents wrapper = restTemplate.getForObject(queryString, NumIncidents.class);
		return sortResults(wrapper.getResults());
	}
	
	
	public String getIncidentsByCountry(String occurCountry, int skipIncidents, int limitIncidents)
	{
		String queryString =
				FDA_BASE_URL+COUNTRY_LATEST_INCIDENTS_QUERY+occurCountry+LIMIT_PREDICATE+limitIncidents
					+SKIP_PREDICATE+skipIncidents;
		
		String wrapper = restTemplate.getForObject(queryString, String.class);
		
		return wrapper;
		
	}
	
	
	  
	
	public static void main(String[] args)
	{
		QueryServiceImpl qis = new QueryServiceImpl();
	    qis.getIncidentsByCountry("US", 2, 2);
		//qis.getNumberOfIncidentsByCounty();
	}
	
	

}
