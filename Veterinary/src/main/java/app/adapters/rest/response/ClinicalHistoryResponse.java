package app.adapters.rest.response;

public class ClinicalHistoryResponse {
	private Long historyId;
    private Long petId;
    private String details;

    public ClinicalHistoryResponse() {}

    public ClinicalHistoryResponse(Long historyId, Long petId, String details) {
        this.historyId = historyId;
        this.petId = petId;
        this.details = details;
    }

    public Long getIdHistory() { return historyId; }
    public void setIdHistory(Long historyId) { this.historyId = historyId; }
    public Long getIdPet() { return petId; }
    public void setIdPet(Long petId) { this.petId = petId; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}
