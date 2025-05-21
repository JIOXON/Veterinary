package app.domain.models;

public class ClinicalHistory {
	private Long historyId;
	private Long PetId;
	private String details;
	
	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}

	public Long getPetId() {
		return PetId;
	}

	public void setPetId(Long petId) {
		PetId = petId;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public ClinicalHistory() {
	}
	@Override
    public String toString() {
        return "ID Historia: " + historyId +
               ", Mascota ID: " + PetId +
               ", Descripción: " + details;
    }
	
}