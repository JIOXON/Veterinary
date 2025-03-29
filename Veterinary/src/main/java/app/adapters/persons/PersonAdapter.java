package app.adapters.persons;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.persons.entity.PersonEntity;
import app.adapters.persons.repository.PersonRepository;
import app.domain.models.Person;
import app.ports.PersonPort;


@Service
public class PersonAdapter implements PersonPort{

	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public boolean existPerson(long document) {
		return personRepository.existByDocument(document);
	}

	@Override
	public void savePerson(Person person) {
		
		PersonEntity personEntity = new PersonEntity(person);
		personRepository.save(personEntity);
		person.setPersonId(personEntity.getPerson_id());
		
	}

	@Override
	public Person findByDocument(long document) {
		PersonEntity personEntity = personRepository.findByDocument(document);
		return PersonEntity(personEntity);
		
	}
	private Person PersonEntity(PersonEntity personEntity) {
		Person person = new Person();
		person.setDocument(personEntity.getDocument());
		person.setName(personEntity.getName());
		person.setAge(personEntity.getAge());
		person.setRole(personEntity.getRole());
		person.setPerson(person);
		return person;
	}

	@Override
	public List<Person> findAllPersons() {

	}
}