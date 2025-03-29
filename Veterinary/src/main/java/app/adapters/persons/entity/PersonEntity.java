package app.adapters.persons.entity;

import app.domain.models.Person;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "person")
public class PersonEntity {
	public PersonEntity(Person person) {
		this.personId = person.getPersonId();
		this.document = person.getDocument();
		this.name = person.getName();
		this.age = person.getAge();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private long personId;
	
	@Column(name = "document")
    private long document;
	
	@Column(name ="age")
    private int age;
	
    @Column(name = "name")
    private String name;
    
    public long getPerson_id() {
        return personId;
    }
    
    public long getDocument() {
        return document;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setDocument(long document) {
        this.document = document;
    }
    
    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public void setName(String name) {
        this.name = name;
    }
    

    public long getPersonId() {
		return personId;
	}

	public PersonEntity() {
    }
}
