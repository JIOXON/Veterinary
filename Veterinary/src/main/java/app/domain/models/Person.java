package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Person {
	private long personId;
    private long document;
    private int age;
    private String role;
    private String name;
    private Person person;
    
    public long getPersonId() {
        return personId;
    }

    public long getDocument() {
        return document;
    }

    public int getAge() {
        return age;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public void setDocument(long document) {
        this.document = document;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person() {
    }
    
}
