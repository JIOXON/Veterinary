package app.ports;

import app.domain.models.Pet;
import app.domain.models.Person;

public interface PetPort {
	public Pet findByPetId(Person personId);
	public void savePet(Pet pet);
}