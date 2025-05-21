package app.adapters.pet;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.adapters.persons.entity.PersonEntity;
import app.adapters.persons.repository.PersonRepository;
import app.adapters.pet.entity.PetEntity;
import app.adapters.pet.repository.PetRepository;
import app.adapters.petOwner.entity.PetOwnerEntity;
import app.adapters.petOwner.repository.PetOwnerRepository;
import app.domain.models.*;

import app.ports.PetPort;

@Service
public class PetAdapter implements PetPort{
	
	@Autowired
    private PetRepository petRepository;
	@Autowired
	private PetOwnerRepository petOwnerRepository;
	@Autowired
	private PersonRepository personRepository;

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
    
    @Override
    public Pet createPet(Pet pet) {
        PetOwnerEntity petOwnerEntity = petOwnerRepository.findById(pet.getOwnerId())
            .orElseThrow(() -> new RuntimeException("No existe un dueño con el ID especificado."));


        PetEntity petEntity = new PetEntity();
        petEntity.setPetName(pet.getPetName());
        petEntity.setOwnerId(petOwnerEntity);
        petEntity.setPetAge(pet.getPetAge());
        petEntity.setSpecies(pet.getSpecies());
        petEntity.setBreed(pet.getBreed());
        petEntity.setCharacteristics(pet.getCharacteristics());
        petEntity.setWeight(pet.getWeight());


        petEntity = petRepository.save(petEntity);
        return petAdapter(petEntity);
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
		PersonEntity person = personRepository.findByDocument(pet.getOwnerId());
		PetOwnerEntity petOwner = petOwnerRepository.findByPerson(person);
	    PetEntity petEntity = new PetEntity(pet, petOwner);
	    petRepository.save(petEntity);
	    pet.setPetId(petEntity.getPetId());
	}
}