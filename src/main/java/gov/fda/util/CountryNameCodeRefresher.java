package gov.fda.util;

import gov.fda.domain.CountryNameCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CountryNameCodeRefresher 
{

	private static final Logger logger = LoggerFactory.getLogger(CountryNameCodeRefresher.class);
	/* 
	 * Although data.okfn.org is authoritative source, during testing it failed few times with Service 500 error.
	 * Due to this we query First From ALT Source ervices.groupkt.com and if that fails we query from STD source.
	 */
	public static final String COUNTRY_QUERY_STRING_STD = "http://data.okfn.org/data/core/country-list/r/data.json";
	public static final String COUNTRY_QUERY_STRING_ALT = "http://services.groupkt.com/country/get/all";
	
	
	
	/*
	 *  This queries the countries list from standard place or alternate source and parse them 
	 *  to list object at most this should be called once per application instance.
	 *  During testing this was failing from Standard sourse many times. 
	 *  so calling the alternate source first then if that fails STD soruce
	 */
	public static  List<CountryNameCode> getCountries()
	{
		
		List<CountryNameCode> listCountries = getCountriesFromALTSoruce();
		if(listCountries.size() != 0){
			return listCountries;
		}
		RestTemplate restTemplate = new RestTemplate();
		String queryString = COUNTRY_QUERY_STRING_STD;
		logger.debug(queryString);
		List<LinkedHashMap<String, String>> countries = restTemplate.getForObject(queryString,  List.class);
		listCountries = new ArrayList<CountryNameCode>();
		for(LinkedHashMap<String, String>country : countries){
				String name = country.get("Name");
				String code = country.get("Code");
		       listCountries.add(new CountryNameCode(name,code));
		}
		return listCountries;
	}
	
	/*
	 * The service from http://services.groupkt.com/country/get/all return he data in different format
	 * than the data.okfn.org,  we should have used Doze to map due to time constraints
	 * using manual code mapper.
	 */
	public static  List<CountryNameCode> getCountriesFromALTSoruce() 
	{
		RestTemplate restTemplate = new RestTemplate();
		String queryString = COUNTRY_QUERY_STRING_ALT;
		logger.debug(queryString);
		List<CountryNameCode> counrtiesReturn = new ArrayList<CountryNameCode>();
		
		try{
			 Country2Code3CodeListWrapper wrapper= 
					restTemplate.getForObject(queryString,  Country2Code3CodeListWrapper.class);
			 
			 ArrayList<CountryName2Code3Code> countires = wrapper.getRestResponse().getResult();
			 for(CountryName2Code3Code country :countires )
			 {
				 counrtiesReturn.add(new CountryNameCode(country.getName(), country.getAlpha2_code()));
			 }
	
		}catch (RestClientException re){
			re.printStackTrace();
			
		};
		
		return counrtiesReturn;
	}

}
