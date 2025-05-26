package app.adapters.rest.request;

public class ClinicalHistoryRequest {
	private long PetId;
    private String details;

    
    public long getPetId() {
		return PetId;
	}
	public void setPetId(long petId) {
		PetId = petId;
	}
	public String getDetails() { 
        
        return details; }
    public void setDetails(String details) { 
        this.details = details;
    }
}
