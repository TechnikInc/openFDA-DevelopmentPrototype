package gov.fda.service;

import gov.fda.domain.CountryResult;
import gov.fda.domain.Result;

import java.util.ArrayList;
import java.util.List;

public interface QueryService 
{
 
	public List<CountryResult> getNumberOfIncidentsByCountyAndDrugName(String drugName);
	
	public List<Integer> getSeriousIncidentsCounts(String occurCountry, String drugName);
	
	public String getDataLastUpdated();
}
