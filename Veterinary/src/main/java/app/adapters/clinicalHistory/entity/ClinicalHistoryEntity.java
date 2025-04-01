package app.adapters.clinicalHistory.entity;

import app.adapters.pet.entity.PetEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "clinical_history")
public class ClinicalHistoryEntity {
	public ClinicalHistoryEntity(Long historyId, PetEntity petEntity, String details) {
		this.historyId = historyId;
		this.petId = petEntity;
		this.details = details;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "history_id")
	private Long historyId;
	
	@JoinColumn (name = "pet_id")
	@OneToOne
	private PetEntity petId;
	
	@Column (name = "details")
	private String details;
	
	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}

	public PetEntity getPetId() {
		return petId;
	}

	public void setPetId(PetEntity petId) {
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
