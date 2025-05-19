package app.ports;

import app.domain.models.Person;
import app.domain.models.Pet;
import java.util.List;

public interface PetPort {
	Pet createPet(Pet pet);
    Pet findPetByPetId(Long PetId);
    boolean existPetByPetId(Long PetId);
    List<Pet> findByOwnerId(Person owner);
    long countByOwnerId(Person owner);
	public void savePet(Pet pet);
}