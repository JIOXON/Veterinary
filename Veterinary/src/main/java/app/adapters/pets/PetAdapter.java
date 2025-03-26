package app.adapters.pets;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Service
public class PetAdapter{
	/*
	@Autowired
	private PetEntity petEntity;
	@Autowired
	private PetRepository petRepository;
	
	@Override
	public Person findByPersonId(Person personId) {
		PersonEntity personEntity = personAdapter(personId);
		PetEntity pet = petRepository.findByPersonId(personEntity);
		return adapter(pet);
	}
	
	private Pet adapter(PetEntity pet) {
		// TODO Auto-generated method stub
		return null;
	}

	private PersonEntity personAdapter(Person person) {
		PersonEntity personEntity = new PersonEntity();
		personEntity.setPersonId(person.getPersonId());
		personEntity.setDocument(person.getDocument());
		personEntity.setName(person.getName());
		personEntity.setRole(person.getRole());
		return personEntity;
	}

	@Override
	public void savePet(Pet pet) {
		// TODO Auto-generated method stub	
	}	
	*/
}