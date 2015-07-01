package gov.fda.util;

/*
 * Different Adverse events and their QueryStrings
 */
public enum SeriousEventType {
	
	ALL(0,"All",""),
	DEATH(1, "Death", "&count=seriousnessdeath"),
	CONG_ANOMALIES(2,"Congenital Anomalies","&count=seriousnesscongenitalanomali"),
	DISABILITY(3, "Disability", "&count=seriousnessdisabling"),
	HOSPITALIZATION(4, "Hospitalization", "&count=seriousnesshospitalization"),
	LIFE_THREATENING(5, "Life Threatening", "&count=seriousnesslifethreatening"),
	UNCLASSIFIED(6, "Unclisified", "&count=seriousnessother");
	
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
