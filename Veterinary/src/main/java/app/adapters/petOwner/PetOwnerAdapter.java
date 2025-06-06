package app.adapters.petOwner;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.persons.entity.PersonEntity;
import app.adapters.persons.repository.PersonRepository;
import app.adapters.pet.entity.PetEntity;
import app.adapters.petOwner.entity.PetOwnerEntity;
import app.adapters.petOwner.repository.PetOwnerRepository;
import app.domain.models.PetOwner;
import app.ports.PetOwnerPort;

@Service
public class PetOwnerAdapter implements PetOwnerPort{
	@Autowired
    private PetOwnerRepository petOwnerRepository;
	@Autowired
    private PersonRepository personRepository;

    @Override
    public boolean existPetOwner(long id) {
        return petOwnerRepository.existsByPersonDocument(id);
    }

    @Override
    public PetOwner findByDocument(long id) {
    	PersonEntity person = personRepository.findByDocument(id);
		PetOwnerEntity petOwner = petOwnerRepository.findByPerson(person);
        return adapterPetOwner(petOwner);
    }

    @Override
    public void savePetOwner(PetOwner Owner) {
    	System.out.println("realiza con datos " + Owner.toString());
        PersonEntity personEntity = personRepository.findByDocument(Owner.getDocument());
        PetOwnerEntity petOwnerEntity = new PetOwnerEntity(Owner, personEntity);
        petOwnerRepository.save(petOwnerEntity);
    }

    @Override
    public List<PetOwner> findAllPetOwners() {
        return petOwnerRepository.findAll().stream()
                .map(this::adapterPetOwner)
                .collect(Collectors.toList());
    }

    private PetOwner adapterPetOwner(PetOwnerEntity petOwnerEntity) {
        PetOwner petOwner = new PetOwner();
        petOwner.setOwnerId(petOwnerEntity.getPerson().getPersonId());
        petOwner.setDocument(petOwnerEntity.getPerson().getDocument());
        petOwner.setName(petOwnerEntity.getPerson().getName());
        petOwner.setAge(petOwnerEntity.getPerson().getAge());
        return petOwner;
    }
}
