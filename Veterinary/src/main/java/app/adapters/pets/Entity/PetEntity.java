package app.adapters.pets.Entity;

import java.sql.Date;

import app.adapters.persons.entity.PersonEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name = "pet")
public class PetEntity {
	public PetEntity(PetEntity petEntity) {
		this.petId = petEntity.getPetId();
		this.personId = petEntity.getPersonId();
		this.petAge = petEntity.getPetAge();
		this.petName = petEntity.getPetName();
		this.characteristics = petEntity.getCharacteristics();
		this.species = petEntity.getSpecies();
		this.breed = petEntity.getBreed();
		this.weight = petEntity.getWeight();
		this.register = petEntity.getRegister();
		
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "petId")
	private long petId;
	@JoinColumn(name="personId")
	@OneToOne
	private PersonEntity personId;
	@Column(name= "petAge")
	private int petAge;
	@Column(name= "petName")
	private String petName;
	@Column(name= "characteristics")
	private String characteristics;
	@Column(name= "species")
    private String species;
	@Column(name= "breed")
    private String breed;
	@Column(name= "weight")
    private String weight;
	@Column(name= "register")
    private Date register;
	
	public PersonEntity getPerson() {
		return personId;
	}
    
    public void setPerson(PersonEntity person) {
		this.personId = person;
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

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public PetEntity() {
    }

	public PersonEntity getPersonId() {
		return personId;
	}

	public void setPersonId(PersonEntity personId) {
		this.personId = personId;
	}
    
}
