package app.adapters.clinicalHistory;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.adapters.clinicalHistory.entity.*;
import app.adapters.clinicalHistory.repository.*;
import app.adapters.pet.entity.*;
import app.domain.models.*;
import app.ports.*;

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
	    ClinicalHistoryEntity entity = new ClinicalHistoryEntity();
	    
	    // Asignar valores de ClinicalHistory al Entity
	    entity.setDetails(clinicalHistory.getDetails());

	    // Recuperar la entidad de la mascota si ya existe
	    PetEntity petEntity = new PetEntity();
	    petEntity.setPetId(clinicalHistory.getPetId());
	    entity.setPetId(petEntity);

	    // Guardar en la base de datos
	    clinicalHistoryRepository.save(entity);

	    // Asignar el ID generado al modelo original
	    clinicalHistory.setHistoryId(entity.getHistoryId());
	}

	//Crear un historial clinico
	@Override
	public ClinicalHistory createClinicalHistory(ClinicalHistory clinicalHistory) {
	    ClinicalHistoryEntity entity = new ClinicalHistoryEntity();

	    // Asignar los valores
	    entity.setDetails(clinicalHistory.getDetails());

	    // Relacionar la mascota
	    PetEntity petEntity = new PetEntity();
	    petEntity.setPetId(clinicalHistory.getPetId());
	    entity.setPetId(petEntity);

	    // Guardar en la base de datos
	    clinicalHistoryRepository.save(entity);

	    // Devolver el objeto guardado convertido en modelo
	    return History(entity);
	}

	
	//Convertir entidad ClinicalHistoryEntity a modelo ClinicalHistory
	private ClinicalHistory History(ClinicalHistoryEntity clinicalHistory) {
	    ClinicalHistory Entity = new ClinicalHistory();
	    Entity.setHistoryId(clinicalHistory.getHistoryId());
	    Entity.setPetId(clinicalHistory.getPetId().getPetId());
	    Entity.setDetails(clinicalHistory.getDetails());
	    return Entity;
	}

	//Encontrar historia clinica por Id
	@Override
	public List<ClinicalHistory> findClinicalHistoryByPetId(PetEntity petEntity) {
		List<ClinicalHistoryEntity> historyEntities = clinicalHistoryRepository.findByPetId(petEntity);
        return historyEntities.stream()
                .map(this::History)
                .collect(Collectors.toList());
	}

}