package gov.fda.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class NumIncidents {
	@JsonProperty("meta")
	private MetaData metaData;
	@JsonProperty("results")
	private ArrayList<Result> results;
	public MetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}

	public ArrayList<Result> getResults() {
		return results;
	}

	public void setResults(ArrayList<Result> results) {
		this.results = results;
	}

	public static void sortResults(List<Result> resultList) {
		   Collections.sort(resultList, new Comparator<Result>() {
		       public int compare(Result r1, Result r2) {
		            int res =  r1.getTerm().compareToIgnoreCase(r2.getTerm());
		            if (res != 0){
		                return res;
		            }
		            return r1.getTerm().compareToIgnoreCase(r2.getTerm());
		       }
		   });
		}

}
