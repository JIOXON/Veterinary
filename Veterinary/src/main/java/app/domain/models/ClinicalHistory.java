package app.domain.models;

public class ClinicalHistory extends Pet{
	private long HistoryId;
	private String Datails;
	
	public long getHistoryId() {
		return HistoryId;
	}
	public void setHistoryId(long historyId) {
		HistoryId = historyId;
	}
	public String getDatails() {
		return Datails;
	}
	public void setDatails(String datails) {
		Datails = datails;
	}
	public ClinicalHistory() {
	}
	
}