package app.adapters.persons;

import java.util.List;
import java.util.stream.Collectors;

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
	public boolean existPerson(long id) {
		return personRepository.existsByDocument(id);
	}

	@Override
	public void savePerson(Person person) {
		PersonEntity personEntity = new PersonEntity(person);
		personRepository.save(personEntity);
		person.setPersonId(personEntity.getPersonId());
	}

	@Override
	public Person findByDocument(long id) {
		PersonEntity personEntity = personRepository.findByDocument(id);
		return adapter(personEntity);
	}
	private Person adapter(PersonEntity personEntity) {
		Person person = new Person();
		person.setDocument(personEntity.getDocument());
		person.setName(personEntity.getName());
		person.setAge(personEntity.getAge());
		return person;
	}

	@Override
    public List<Person> findAllPersons() {
        return personRepository.findAll().stream()
                .map(this::adapter)
                .collect(Collectors.toList());
    }

}