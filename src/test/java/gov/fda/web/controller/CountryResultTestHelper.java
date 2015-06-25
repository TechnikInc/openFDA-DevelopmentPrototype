package gov.fda.web.controller;

import gov.fda.domain.CountryResult;
import gov.fda.service.QueryServiceImpl;

public class CountryResultTestHelper {
	
	public static CountryResult  createDTO(String shortName, int totalIncidents, String countryName) {
        CountryResult dto = new CountryResult();
        dto.setTerm(shortName);
        dto.setCount(totalIncidents);
        dto.setCountryName(countryName);
        dto.setImageSrc(QueryServiceImpl.COUNTRY_FLAG_URL+shortName+QueryServiceImpl.FLAG_FILE_EXTENSION);
        return dto;
    }

}
