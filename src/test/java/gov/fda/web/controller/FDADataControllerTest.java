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
		String countryShortName = "US";
		int totalIncidents = 10;
		String countryName = "United States";
		String drugName = "Aspirin";
		CountryResult country = CountryResultTestHelper.createDTO(countryShortName, totalIncidents, countryName);
		CountryResult expectedCountry = CountryResultTestHelper.createDTO(countryShortName, totalIncidents, countryName);
		
		// Mock expected countries
	    when(queryServiceMock.getNumberOfIncidentsByCountyAndDrugName(drugName )).thenReturn(Arrays.asList(expectedCountry));

		
		List<CountryResult> results = dataControllerMock.getCountriesAndIncidents(drugName);
		// verify results null;
		assertNotNull(results);
		// verify results size
		assertEquals(1, results.size());
		// verify result content
		verifyCountry(country, results.get(0));
	}
	

	@Test
	public void testSeriousIncidentsCountForACountryAndDrugName()
	{
		String countryName = "US";
		String drugName = "Aspirin";
		List<Integer> seriounsIncidents = new ArrayList<Integer>();
		
		seriounsIncidents.add(new Integer(110));
		seriounsIncidents.add(new Integer(70));
		seriounsIncidents.add(new Integer(60));
		seriounsIncidents.add(new Integer(50));
		seriounsIncidents.add(new Integer(150));
		seriounsIncidents.add(new Integer(0));
		
	    List<Integer> expected = new ArrayList<Integer>();
	    expected.add(new Integer(110));
	    expected.add(new Integer(70));
	    expected.add(new Integer(60));
	    expected.add(new Integer(50));
	    expected.add(new Integer(150));
	    expected.add(new Integer(0));
		
		
		// Mock expected countries
	   when(queryServiceMock.getSeriousIncidentsCounts(countryName, drugName)).thenReturn(expected);
		
		List<Integer> results = dataControllerMock.getCountrySeriousIncidentCounts(countryName, drugName);
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
