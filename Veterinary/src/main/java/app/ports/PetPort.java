package app.ports;

import app.domain.models.Person;
import app.domain.models.Pet;
import java.util.List;

public interface PetPort {
	public Pet createPet(Pet pet);
	public Pet findPetByPetId(Long petId);
	public boolean existPetByPetId(Long petId);
	public List<Pet> findByOwnerId(Person owner);
	public long countByOwnerId(Person owner);
	public void savePet(Pet pet);
}