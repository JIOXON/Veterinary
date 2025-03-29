package app.adapters.clinicalHistory;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.adapters.clinicalHistory.entity.ClinicalHistoryEntity;
import app.adapters.clinicalHistory.repository.ClinicalHistoryRepository;
import app.domain.models.ClinicalHistory;
import app.ports.ClinicalHistoryPort;

@Service
public class ClinicalHistoryAdapter implements ClinicalHistoryPort{

	@Autowired
    private ClinicalHistoryRepository clinicalHistoryRepository;
	
	//Verificacion
	@Override
	public boolean existClinicalHistoryId(long HistoryId) {
		return clinicalHistoryRepository.existsById(HistoryId);
	}
	
	//Metodo para guardar en la base de datos
	@Override
	public void saveClinicalHistory(ClinicalHistory clinicalHistory) {
		ClinicalHistoryEntity Entity = new ClinicalHistoryEntity();
		clinicalHistoryRepository.save(Entity);
		clinicalHistory.setHistoryId(Entity.getHistoryId());
	}
	//Crear un historial clinico
	@Override
	public ClinicalHistory createClinicalHistory(ClinicalHistory clinicalHistory) {
		ClinicalHistoryEntity Entity = new ClinicalHistoryEntity();
		clinicalHistoryRepository.save(Entity);
		//Devolver el objeto guardado
        return History(Entity);
	}
	
	//Convertir entidad ClinicalHistoryEntity a modelo ClinicalHistory
	private ClinicalHistory History(ClinicalHistoryEntity clinicalHistory) {
		ClinicalHistory Entity = new ClinicalHistory();
		Entity.setHistoryId(clinicalHistory.getHistoryId());
		Entity.setPetId(clinicalHistory.getPetId());
		Entity.setDetails(clinicalHistory.getDetails());
		//Devolver el objeto guardado
        return Entity;
    }

	//Encontrar historia clinica por Id
	@Override
	public List<ClinicalHistory> findClinicalHistoryByPetId(long petId) {
		List<ClinicalHistoryEntity> historyEntities = clinicalHistoryRepository.findByPetId(petId);
        return historyEntities.stream()
                .map(this::History)// Convierte la entidad en un modelo usando el metodo History
                .collect(Collectors.toList());
	}

}