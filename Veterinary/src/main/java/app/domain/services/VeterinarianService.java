package app.domain.services;

import app.domain.models.Pet;
import app.domain.models.Person;
import app.ports.PersonPort;
public class VeterinarianService {
	
	private PersonPort personPort;
	
	public void registerOwner(Person owner)throws Exception{
        if (personPort.existPerson(owner.getDocument())){
            throw new Exception("Ya existe una persona con esa Cedula");
        }
        personPort.savePerson(owner);
    }
	
	public void registerPet(Pet pet)throws Exception{
		if (personPort.existPerson(pet.getDocument())){
            throw new Exception("Ya existe una mascota con ese Dueño");
        }
	}
}