package gov.fda.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountrySeriousIncidents {
	int deathCount;
	int congCount;
	int disabilityCount;
	int hospitalizationCount;
	int lifeThreateningCount;
	int unclassifiedCount;
	public int getDeathCount() {
		return deathCount;
	}
	public void setDeathCount(int deathCount) {
		this.deathCount = deathCount;
	}
	public int getCongCount() {
		return congCount;
	}
	public void setCongCount(int congCount) {
		this.congCount = congCount;
	}
	public int getDisabilityCount() {
		return disabilityCount;
	}
	public void setDisabilityCount(int disabilityCount) {
		this.disabilityCount = disabilityCount;
	}
	public int getHospitalizationCount() {
		return hospitalizationCount;
	}
	public void setHospitalizationCount(int hospitalizationCount) {
		this.hospitalizationCount = hospitalizationCount;
	}
	public int getLifeThreateningCount() {
		return lifeThreateningCount;
	}
	public void setLifeThreateningCount(int lifeThreateningCount) {
		this.lifeThreateningCount = lifeThreateningCount;
	}
	public int getUnclassifiedCount() {
		return unclassifiedCount;
	}
	public void setUnclassifiedCount(int unclassifiedCount) {
		this.unclassifiedCount = unclassifiedCount;
	}
	
	

}
