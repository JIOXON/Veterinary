package app.adapters.petOwner.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.adapters.persons.entity.PersonEntity;
import app.adapters.pet.entity.PetEntity;
import app.adapters.petOwner.entity.PetOwnerEntity;

public interface PetOwnerRepository extends JpaRepository<PetOwnerEntity, Long>{
	boolean existsByPersonDocument(long document);
    PetOwnerEntity findByPerson(PersonEntity id);
	Optional<PetOwnerEntity> findByPersonDocument(long ownerId);
}
