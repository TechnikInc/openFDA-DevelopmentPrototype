package gov.fda.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryAndIncidents {
	private MetaData metaData;
	private ArrayList<CountryResult> results;

	public MetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}

	

	public ArrayList<CountryResult> getResults() {
		return results;
	}

	public void setResults(ArrayList<CountryResult> results) {
		this.results = results;
	}

	public static void sortList(List<CountryResult> resultList) {
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
	
	public static String getCountryName(String code, List<CountryNameCode> countries) {
		
		for(CountryNameCode country : countries){
			if(country.getCode().equalsIgnoreCase(code)){
				return country.getName();
			}
		}
		return null;
		  
	}

}
