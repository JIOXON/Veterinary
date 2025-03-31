package app.domain.models;

public class Person {
	private long personId;
    private long document;
    private int age;
    private String name;

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public long getDocument() {
		return document;
	}

	public void setDocument(long document) {
		this.document = document;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person() {
    }
    
}
