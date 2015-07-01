package gov.fda.web.controller;

import gov.fda.domain.CountryResult;
import gov.fda.util.Constants;

public class CountryResultTestHelper {
	
	public static CountryResult  createDTO(String shortName, int totalIncidents, String countryName) {
        CountryResult dto = new CountryResult();
        dto.setTerm(shortName);
        dto.setCount(totalIncidents);
        dto.setCountryName(countryName);
        dto.setImageSrc(Constants.COUNTRY_FLAG_URL+shortName+Constants.FLAG_FILE_EXTENSION);
        return dto;
    }

}
