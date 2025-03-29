package app.ports;

import app.domain.models.Pet;
import java.util.List;

public interface PetPort {
	public Pet createPet(Pet pet);
	public Pet updatePet(Pet pet);
	public void deletePet(Long idPet);
	public Pet findPetByIdPet(Long idPet);
	public boolean existPetByIdPet(Long idPet);
	public List<Pet> findByOwnerId(long ownerId);
	public long countByOwnerId(long ownerId);
}