package app.adapters.petOwner.entity;

import app.adapters.persons.entity.PersonEntity;
import app.domain.models.PetOwner;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "pet_owner")
public class PetOwnerEntity {
	public PetOwnerEntity(PetOwner petOwner,  PersonEntity personEntity) {
        this.ownerId = petOwner.getOwnerId();
        this.person=personEntity;
    }
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private long ownerId;

    @JoinColumn(name="person_id")
    @OneToOne
    private PersonEntity person;
    
    
	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public PersonEntity getPerson() {
		return person;
	}

	public void setPerson(PersonEntity person) {
		this.person = person;
	}

	public PetOwnerEntity() {
		
	}
    
}