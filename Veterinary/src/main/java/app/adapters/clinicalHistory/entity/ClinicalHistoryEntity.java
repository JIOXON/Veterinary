package app.adapters.clinicalHistory.entity;

import app.domain.models.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import app.domain.models.ClinicalHistory;

@Entity
@Table (name = "ClinicalHistory")
public class ClinicalHistoryEntity {
	public ClinicalHistoryEntity(ClinicalHistory clinical) {
		this.historyId = clinical.getHistoryId();
		this.petId = clinical.getPetId();
		this.details = clinical.getDetails();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "historyId")
	private long historyId;
	
	@Column (name = "petId")
	private Pet petId;
	
	@Column (name = "details")
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

	public ClinicalHistoryEntity() {
		
	}
}
