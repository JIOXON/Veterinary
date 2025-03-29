package app.domain.models;

public class ClinicalHistory {
	private long historyId;
	private Pet petId;
	private String details;
	
	public long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(long historyId) {
		this.historyId = historyId;
	}

	public Pet getPetId() {
		return petId;
	}

	public void setPetId(Pet petId) {
		this.petId = petId;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public ClinicalHistory() {
	}
	
}