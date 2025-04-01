package app.ports;

import java.util.List;
import app.adapters.pet.entity.PetEntity;
import app.domain.models.ClinicalHistory;

public interface ClinicalHistoryPort {
	public boolean existClinicalHistoryId(long historyId);
	public void saveClinicalHistory(ClinicalHistory clinicalHistory);
	public ClinicalHistory createClinicalHistory(ClinicalHistory clinicalHistory);
	public List<ClinicalHistory> findClinicalHistoryByPetId(PetEntity pet);
}