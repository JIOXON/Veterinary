package app.adapters.pet;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.adapters.persons.entity.PersonEntity;
import app.adapters.pet.entity.PetEntity;
import app.adapters.pet.repository.PetRepository;
import app.domain.models.*;

import app.ports.PetPort;

@Service
public class PetAdapter implements PetPort{
	
	@Autowired
    private PetRepository petRepository;

    @Override
    public Pet createPet(Pet pet) {
        PetEntity petEntity = new PetEntity();
        petRepository.save(petEntity);
        return petAdapter(petEntity);
    }

    @Override
	public Pet findPetByPetId(Long PetId) {
    	PetEntity petEntity = petRepository.findById(PetId).orElse(null);
        return petAdapter(petEntity);
	}

	@Override
	public boolean existPetByPetId(Long PetId) {
		return petRepository.existsById(PetId);
	}

	@Override
    public List<Pet> findByOwnerId(Person owner) {
        PersonEntity ownerId = personAdapter(owner);
        return petRepository.findByOwnerId(ownerId).stream()
                .map(this::petAdapter)
                .collect(Collectors.toList());
    }

    @Override
    public long countByOwnerId(Person owner) {
    	PersonEntity Entity = personAdapter(owner);
    	return petRepository.countByOwnerId(Entity);
    }

    private Pet petAdapter(PetEntity petEntity) {
        Pet pet = new Pet();
        pet.setPetId(petEntity.getPetId());
        pet.setPetName(petEntity.getPetName());
        pet.setOwnerId(petEntity.getOwnerId().getOwnerId());
        pet.setPetAge(petEntity.getPetAge());
        pet.setSpecies(petEntity.getSpecies());
        pet.setBreed(petEntity.getBreed());
        pet.setCharacteristics(petEntity.getCharacteristics());
        pet.setWeight(petEntity.getWeight());
        return pet;
    }
    
    private PersonEntity personAdapter(Person person) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setPersonId(person.getPersonId());
        personEntity.setDocument(person.getDocument());
        personEntity.setName(person.getName());
        personEntity.setAge(person.getAge());
        return personEntity;
    }

	@Override
	public void savePet(Pet pet) {
	    PetEntity petEntity = new PetEntity(pet);
	    petRepository.save(petEntity);
	    pet.setPetId(petEntity.getPetId());
	}
}