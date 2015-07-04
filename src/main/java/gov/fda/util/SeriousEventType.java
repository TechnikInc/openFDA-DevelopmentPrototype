package gov.fda.util;

/*
 * Different Adverse events and their QueryStrings
 */
public enum SeriousEventType {
	
	ALL(0,"All",""),
	DEATH(1, "Death", "seriousnessdeath"),
	CONG_ANOMALIES(2,"Congenital Anomalies","seriousnesscongenitalanomali"),
	DISABILITY(3, "Disability", "seriousnessdisabling"),
	HOSPITALIZATION(4, "Hospitalization", "seriousnesshospitalization"),
	LIFE_THREATENING(5, "Life Threatening", "seriousnesslifethreatening"),
	UNCLASSIFIED(6, "Unclisified", "seriousnessother");
	
	private int typeId;
	private String typeValue;
	private String predicate;
	
	private SeriousEventType(int typeId, String typeValue, String predicate)
	{
		this.typeId = typeId;
		this.typeValue = typeValue;
		this.predicate = predicate;
	}

	public int typeId() {
		return typeId;
	}

	public String typeValue() {
		return typeValue;
	}


	public String predicate() {
		return predicate;
	}

	
	public static SeriousEventType parse(int typeId)
	{
		for (SeriousEventType e : SeriousEventType.values()) {
			if (e.typeId()==typeId) {
				return e;
			}
		}
		return null;
	}
}
