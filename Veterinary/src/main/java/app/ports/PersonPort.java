package app.ports;

import app.domain.models.Person;
import java.util.List;

public interface PersonPort {
	public boolean existPerson(long document);
	public void savePerson(Person person);
	public Person findByDocument(long id);
	public List<Person> findAllPersons();
}