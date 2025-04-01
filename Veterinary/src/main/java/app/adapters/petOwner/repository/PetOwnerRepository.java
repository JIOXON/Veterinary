package app.adapters.petOwner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.adapters.persons.entity.PersonEntity;
import app.adapters.petOwner.entity.PetOwnerEntity;

public interface PetOwnerRepository extends JpaRepository<PetOwnerEntity, Long>{
	boolean existsByPersonDocument(long document);
    PetOwnerEntity findByPerson(PersonEntity id);
}
