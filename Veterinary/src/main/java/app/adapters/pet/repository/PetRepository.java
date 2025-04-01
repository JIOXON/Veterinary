package app.adapters.pet.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import app.adapters.persons.entity.PersonEntity;
import app.adapters.pet.entity.PetEntity;

public interface PetRepository extends JpaRepository<PetEntity, Long> {
	public PetEntity findPetByPetId(Long id);
	public List<PetEntity> findByOwnerId(PersonEntity ownerId);
	long countByOwnerId(PersonEntity owner);
}
