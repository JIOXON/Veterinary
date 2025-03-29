package app.adapters.clinicalHistory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import app.adapters.clinicalHistory.entity.ClinicalHistoryEntity;
import app.domain.models.ClinicalHistory;

public interface ClinicalHistoryRepository extends JpaRepository<ClinicalHistoryEntity, Long> {
    public ClinicalHistory findById(long historyId);
    public List<ClinicalHistoryEntity> findByPetId(long petId);
}