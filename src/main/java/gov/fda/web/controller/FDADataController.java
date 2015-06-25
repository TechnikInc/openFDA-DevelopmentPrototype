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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/query")
public class FDADataController {

	private final Logger logger = LoggerFactory.getLogger(FDADataController.class);
	
	@Autowired
    QueryService queryService;


	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public List<CountryResult> getCountriesAndIncidents() {
		logger.debug("getCountiesAndIncidents() is executed!");
		return queryService.getNumberOfIncidentsByCounty();
	}
	
	@RequestMapping(value = "/countryCode/{code}/limit/{sizeLimit}/skip/{skipSize}", method = RequestMethod.GET)
	public String getCountryIncident(@PathVariable String code,
			@PathVariable int sizeLimit, @PathVariable int skipSize) {
		logger.debug("getCountryIncident() is executed! Code :"+code);
		logger.debug("getCountryIncident() is executed! limitSize :"+sizeLimit);
		logger.debug("getCountryIncident() is executed! skipSize :"+skipSize);
		return queryService.getIncidentsByCountry(code, sizeLimit, skipSize);
	}

}