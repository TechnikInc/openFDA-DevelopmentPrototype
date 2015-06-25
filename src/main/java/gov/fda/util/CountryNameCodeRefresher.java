package gov.fda.util;

import gov.fda.domain.CountryNameCode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class CountryNameCodeRefresher 
{

	private static final Logger logger = LoggerFactory.getLogger(CountryNameCodeRefresher.class);
	
	public static  List<CountryNameCode> getCountries()
	{
		RestTemplate restTemplate = new RestTemplate();
		String queryString =
				"http://data.okfn.org/data/core/country-list/r/data.json";
		logger.debug(queryString);
		
		List<LinkedHashMap<String, String>> countries = restTemplate.getForObject(queryString,  List.class);
		List<CountryNameCode> listCountries = new ArrayList<CountryNameCode>();
		for(LinkedHashMap<String, String>country : countries){
				String name = country.get("Name");
				String code = country.get("Code");
		       listCountries.add(new CountryNameCode(name,code));
		}
		return listCountries;
	}

}
