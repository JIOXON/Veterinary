package app.adapters.rest.request;

public class PetRequest {
	private String name;
    private Long ownerId;
    private int age;
    private String species;
    private String breed;
    private String characteristics;
    private float weight;

    public String getName() { 
        return name; 
    }
    public void setName(String name) { 
        this.name = name; 
    }
    
    public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	public int getAge() { 
        return age; 
    }
    public void setAge(int age) { 
        this.age = age; 
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
	public String getCharacteristics() { 
        return characteristics; 
    }
    public void setCharacteristics(String characteristics) { 
        this.characteristics = characteristics; 
    }
    public float getWeight() { 
        return weight; 
    }
    public void setWeight(float weight) { 
        this.weight = weight; 
    }
}
