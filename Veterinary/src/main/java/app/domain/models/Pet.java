package app.domain.models;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class Pet extends Person{
    private long petId;
    private int petAge;
	private String petName;
	private String characteristics;
    private String species;
    private String breed;
    private String weight;
    private Date register;
    private Person person;
    
    public Person getPerson() {
		return person;
	}
    
    public void setPerson(Person person) {
		this.person = person;
	}
    
    public String getCharacteristics() {
        return characteristics;
    }
    
    public String getPetName() {
        return petName;
    }

    public String getSpecies() {
        return species;
    }
    
    public int getPetAge() {
        return petAge;
    }

    public String getBreed() {
        return breed;
    }

    public String getWeight() {
        return weight;
    }

    public Date getRegister() {
        return register;
    }

    public long getPetId() {
        return petId;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }
    
    public void setPetName(String petName) {
        this.petName = petName;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
    
    public void setPetAge(int petAge) {
        this.petAge = petAge;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setRegister(Date register) {
        this.register = register;
    }

    public void setPet_id(long petId) {
        this.petId = petId;
    }

    public Pet() {
    }

}
