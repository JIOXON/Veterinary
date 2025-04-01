package app.adapters.pet.entity;

import app.adapters.petOwner.entity.PetOwnerEntity;
import app.domain.models.Pet;
import jakarta.persistence.*;

@Entity
@Table(name = "pet")
public class PetEntity {
	public PetEntity(Pet pet, PetOwnerEntity petOwnerEntity) {
		this.petId = pet.getPetId();
		this.ownerId = petOwnerEntity;
		this.petAge = pet.getPetAge();
		this.petName = pet.getPetName();
		this.characteristics = pet.getCharacteristics();
		this.species = pet.getSpecies();
		this.breed = pet.getBreed();
		this.weight = pet.getWeight();
	}
	public PetEntity(Pet pet) {
	    this.petId = pet.getPetId();
	    this.petAge = pet.getPetAge();
	    this.petName = pet.getPetName();
	    this.characteristics = pet.getCharacteristics();
	    this.species = pet.getSpecies();
	    this.breed = pet.getBreed();
	    this.weight = pet.getWeight();
	}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private long petId;
    
    @JoinColumn(name = "owner_id")
    @OneToOne
    private PetOwnerEntity ownerId;
    
    @Column(name = "pet_age")
    private int petAge;
    
    @Column(name = "pet_name")
    private String petName;
    
    @Column(name = "characteristics")
    private String characteristics;
    
    @Column(name = "species")
    private String species;
    
    @Column(name = "breed")
    private String breed;
    
    @Column(name = "weight")
    private float weight;

	public long getPetId() {
		return petId;
	}

	public void setPetId(long petId) {
		this.petId = petId;
	}

	public PetOwnerEntity getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(PetOwnerEntity ownerId) {
		this.ownerId = ownerId;
	}

	public int getPetAge() {
		return petAge;
	}

	public void setPetAge(int petAge) {
		this.petAge = petAge;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public PetEntity() {}
}