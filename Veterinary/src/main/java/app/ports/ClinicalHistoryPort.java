package app.ports;

import java.util.List;
import app.domain.models.ClinicalHistory;

public interface ClinicalHistoryPort {
	public boolean existClinicalHistoryId(long HistoryId);
	public void saveClinicalHistory(ClinicalHistory clinicalHistory);
	public ClinicalHistory createClinicalHistory(ClinicalHistory clinicalHistory);
	public List<ClinicalHistory> findClinicalHistoryByPetId(long petId);
}