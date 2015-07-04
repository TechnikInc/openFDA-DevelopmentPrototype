package gov.fda.web.controller;

import gov.fda.domain.CountryResult;
import gov.fda.domain.Result;
import gov.fda.service.QueryService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/query")
public class FDADataController {

	private final Logger logger = LoggerFactory.getLogger(FDADataController.class);
	
	@Autowired
    QueryService queryService;
	
	@RequestMapping(value = "/countries/{drugName}", method = RequestMethod.GET)
	public List<CountryResult> getCountriesAndIncidents(@PathVariable String drugName) {
		logger.debug("getCountriesAndIncidents() is executed! drugName :"+drugName);
		return queryService.getNumberOfIncidentsByCountyAndDrugName(drugName);
	}
	
	
	@RequestMapping(value = "/seriousIncidents/{countryCode}/{drugName}", method = RequestMethod.GET)
	@ResponseBody()
	public List<Integer> getCountrySeriousIncidentCounts(@PathVariable String countryCode,
			@PathVariable String drugName) {
		logger.debug("getCountrySeriousIncidentCounts() is executed! countryCode :"+countryCode);
		logger.debug("getCountrySeriousIncidentCounts() is executed! drugName :"+drugName);
		return queryService.getSeriousIncidentsCounts(countryCode, drugName);
	}
	
	@RequestMapping(value = "/dataLastUpdated", method = RequestMethod.GET)
	@ResponseBody()
	public String dataLastUpdated() {
		logger.debug("dataLastUpdated() is executed!");
		return queryService.getDataLastUpdated();
	}

}