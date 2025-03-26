package app.adapters.pets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.adapters.persons.entity.PersonEntity;
import app.adapters.pets.Entity.PetEntity;


public interface PetRepository extends JpaRepository<PetEntity, Long>{

	public PetEntity findByPersonId(PersonEntity personEntity);
	
}
