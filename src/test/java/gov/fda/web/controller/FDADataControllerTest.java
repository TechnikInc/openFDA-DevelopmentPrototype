package gov.fda.web.controller;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import gov.fda.domain.CountryResult;
import gov.fda.service.QueryService;
import gov.fda.service.QueryServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.Validator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestContext.class})
public class FDADataControllerTest {
	
	
	
    private QueryService queryServiceMock;
	
	
	private FDADataController dataControllerMock;
	
	 @Resource
	 private Validator validator;
	
	
	 @Before
	 public void setUp() {
		 dataControllerMock = new FDADataController();
		 queryServiceMock = mock(QueryServiceImpl.class);
		 dataControllerMock.queryService = queryServiceMock;
	}
	@Test
	public void test_country_and_reported_count()
	{
		String shortName = "US";
		int totalIncidents = 10;
		String countryName = "United States";
		CountryResult country = CountryResultTestHelper.createDTO(shortName, totalIncidents, countryName);
		CountryResult expectedCountry = CountryResultTestHelper.createDTO(shortName, totalIncidents, countryName);
		
		// Mock expected countries
	    when(queryServiceMock.getNumberOfIncidentsByCounty()).thenReturn(Arrays.asList(expectedCountry));

		
		List<CountryResult> results = dataControllerMock.getCountriesAndIncidents();
		// verify results null;
		assertNotNull(results);
		// verify results size
		assertEquals(1, results.size());
		// verify result content
		verifyCountry(country, results.get(0));
	}
	

	@Test
	public void test_incident_in_a_country()
	{
		String  result = "US";
		String expected = "US";
		
		String countryShortName  = "US";
		int limitResults = 1;
		int skipResults = 1;
		
		// Mock expected countries
	    when(queryServiceMock.getIncidentsByCountry(countryShortName, skipResults,limitResults )).thenReturn(expected);
		
		String results = dataControllerMock.getCountryIncident(countryShortName, skipResults,limitResults);
		// verify results not null;
		assertNotNull(results);
		// verify results content
		assertEquals(results, expected);
	}
	
	private void verifyCountry(CountryResult result, CountryResult original)
	{
		assertEquals(result.getTerm(), original.getTerm());		
		assertEquals(result.getCount(), original.getCount());
		assertEquals(result.getCountryName(), original.getCountryName());
		assertEquals(result.getImageSrc(), original.getImageSrc());
		
	}

}
