package gov.fda.service;
import static org.mockito.Mockito.mock;
import gov.fda.domain.DeseaseAndInsidents;
import gov.fda.domain.MetaData;
import gov.fda.domain.Result;
import gov.fda.util.Constants;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class QuerySerivceImplTest 
{

	QueryServiceImpl queryService;
	RestTemplate restTemplate;
	 
	
	public  void setup()
	{
		queryService = mock(QueryServiceImpl.class);
		restTemplate = mock(RestTemplate.class);
		queryService.restTemplate = restTemplate;
		
	}
	
	private DeseaseAndInsidents setupResults()
	{
		ArrayList<Result> results = new ArrayList<Result>();
		Result r = new Result();
		r.setCount(100);
		r.setTerm("diabetes");
		results.add(r);
		Result r1 = new Result();
		r.setCount(200);
		r.setTerm("hypertension");
		results.add(r1);
		DeseaseAndInsidents  dai = new DeseaseAndInsidents();
		dai.setMetaData(new MetaData());
		dai.setResults(results);
		return dai;
	}
	
	@Test
	public void test_desease_and_number_of_indidents_query()
	{
		setup();
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		MockRestServiceServer mockServer = 
				MockRestServiceServer.createServer(restTemplate);
		//List<Result> results = queryService.getNumberOfIncidentsByCounty();
		String queryString =
				Constants.FDA_BASE_URL+Constants.AND_OPERATION+Constants.DESEASE_AND_NUM_INCIDENTS_QUERY;
//		when(restTemplate.getForObject(queryString, DeseaseAndInsidents.class)).thenReturn(setupResults());
		
	   // when(restTemplate.getForObject(queryString, Any.class)).thenReturn(setupResults());
	    
	   // when(restTemplate.getForObject("http://example.com", String.class)).thenReturn("hello world");


		
		//assertNotNull(results);*/
		
		
	}
}
