package gov.fda.util;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country2Code3CodeListWrapper {
	@JsonProperty("RestResponse")
	private RestResponse RestResponse;

	public RestResponse getRestResponse() {
		return RestResponse;
	}

	public void setRestResponse(RestResponse restResponse) {
		RestResponse = restResponse;
	}

}
