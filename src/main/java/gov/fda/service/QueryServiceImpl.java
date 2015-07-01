package gov.fda.service;

import gov.fda.domain.CountryAndIncidents;
import gov.fda.domain.CountryNameCode;
import gov.fda.domain.CountryResult;
import gov.fda.domain.CountrySeriousIncidents;
import gov.fda.domain.NumIncidents;
import gov.fda.domain.Result;
import gov.fda.util.Constants;
import gov.fda.util.CountryNameCodeRefresher;
import gov.fda.util.SeriousEventType;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class QueryServiceImpl implements QueryService{
	
	protected  RestTemplate restTemplate;
	
	protected List<CountryNameCode> countries;
	
	
	private static final Logger logger = LoggerFactory.getLogger(QueryServiceImpl.class);
	
	/*
	 * 	FDA data does not have unique country codes and names as per API.
	 *  so we query the country code names from http://data.okfn.org/data/core/country-list/r/data.json these will be loaded in 
	 *  countries instance
	 */
	public void loadCountriesStaticData()
	{
		if(countries == null || countries.size() == 0)
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
				Constants.FDA_BASE_URL+Constants.COUNTRY_AND_NUM_INCIDENTS_QUERY;
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
	
	
	public List<CountryResult> getNumberOfIncidentsByCountyAndDrugName(String drugName){
		this.restTemplate = new RestTemplate();
		String queryString =
				Constants.FDA_BASE_URL+
				Constants.SEARCH_PREDICATE+
				Constants.DRUG_SEARCH+drugName +
				Constants.COUNTRY_AND_NUM_INCIDENTS_QUERY;
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
		return Constants.COUNTRY_FLAG_URL+countryShortName+Constants.FLAG_FILE_EXTENSION;
	}
	
	
	public List<Result> getNumberOfIncidentsByDrug(){
		this.restTemplate = new RestTemplate();
		String queryString =
				Constants.FDA_BASE_URL+Constants.DRUG_AND_NUM_INCIDENTS_QUERY;
		logger.debug(queryString);
		NumIncidents wrapper = restTemplate.getForObject(queryString, NumIncidents.class);
		return sortResults(wrapper.getResults());
	}
	
	
	public String getIncidentsByCountry(String occurCountry, int skipIncidents, int limitIncidents)
	{
		String queryString =
				Constants.FDA_BASE_URL+
					Constants.COUNTRY_LATEST_INCIDENTS_QUERY+occurCountry+
					Constants.LIMIT_PREDICATE+limitIncidents
					+Constants.SKIP_PREDICATE+skipIncidents;
		
		String wrapper = restTemplate.getForObject(queryString, String.class);
		
		return wrapper;
		
	}
	
	private int getSeriousEventsByCountry(String occurCountry, SeriousEventType event, String drugName)
	{
		int returnCount = 0;	
		String queryString =
				Constants.FDA_BASE_URL+Constants.COUNTRY_LATEST_INCIDENTS_QUERY+occurCountry+
				Constants.AND_OPERATION+Constants.DRUG_SEARCH+drugName+
				event.predicate();
		
		
		
		try{
			URI uri = URI.create(queryString);
			logger.debug(uri.toURL().toString());
			NumIncidents wrapper = restTemplate.getForObject(uri, NumIncidents.class);
			return wrapper.getResults().get(0).getCount();
		}catch(Exception e)
		{
			return 0;
		}
	}
	
	public List<Integer> getSeriousIncidentsCounts(String occurCountry, String drugName)
	{
		CountrySeriousIncidents csi = new CountrySeriousIncidents();
		csi.setDeathCount( getSeriousEventsByCountry(occurCountry, SeriousEventType.DEATH,  drugName));
		csi.setCongCount( getSeriousEventsByCountry(occurCountry, SeriousEventType.CONG_ANOMALIES,  drugName));
		csi.setDisabilityCount( getSeriousEventsByCountry(occurCountry, SeriousEventType.DISABILITY,  drugName));
		csi.setHospitalizationCount( getSeriousEventsByCountry(occurCountry, SeriousEventType.HOSPITALIZATION,  drugName));
		csi.setLifeThreateningCount( getSeriousEventsByCountry(occurCountry, SeriousEventType.LIFE_THREATENING,  drugName));
		csi.setUnclassifiedCount( getSeriousEventsByCountry(occurCountry, SeriousEventType.UNCLASSIFIED,  drugName));
		List<Integer> returnValue = new ArrayList<Integer>();
		returnValue.add(csi.getDeathCount());
		returnValue.add(csi.getCongCount());
		returnValue.add(csi.getDisabilityCount());
		returnValue.add(csi.getHospitalizationCount());
		returnValue.add(csi.getLifeThreateningCount());
		returnValue.add(csi.getUnclassifiedCount());
		System.out.println("Incident Values :"+returnValue);
		return returnValue;
		
	}
	
	public static void main(String[] args)
	{
		QueryServiceImpl qsi = new QueryServiceImpl();
		//qsi.getNumberOfIncidentsByCountyAndDrugName("aspirin");
		qsi.getSeriousIncidentsCounts("ae","aspirin");
	}

}
