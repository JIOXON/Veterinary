package app.ports;

import java.util.List;
import app.domain.models.PetOwner;

public interface PetOwnerPort {
	public boolean existPetOwner(long document);
	public void savePetOwner(PetOwner petOwner);
	public PetOwner findByDocument(long id);
	public List<PetOwner> findAllPetOwners();
}
