package gov.fda.service;

import gov.fda.domain.CountryResult;
import gov.fda.domain.Result;

import java.util.ArrayList;
import java.util.List;

public interface QueryService 
{
	public List<Result> getNumberOfIncidentsByDrug();
	
	public List<CountryResult> getNumberOfIncidentsByCounty();
	
	public String getIncidentsByCountry(String occurCountry, int skipIncidents, int queryIncidents);   
}
