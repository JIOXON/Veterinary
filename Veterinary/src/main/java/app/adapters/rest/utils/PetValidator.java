package app.adapters.rest.utils;

import org.springframework.stereotype.Component;

@Component
public class PetValidator extends SimpleValidator{
	
	public boolean validatePetID(String petID) {
        return petID != null && !petID.trim().isEmpty();
    }

    public boolean validateOwnerID(String ownerID) {
        return ownerID != null && !ownerID.trim().isEmpty();
    }

    public long validatePetAge(String value) throws Exception {
        return longValidator(value, "edad de la mascota");
    }

    public float validateWeight(String value) throws Exception {
        return floatValidator(value, "peso de la mascota");
    }
    public String nameValidator(String value) throws Exception {
		return stringValidator(value, "nombre de la mascota ");
	}
    public int ageValidator(String value) throws Exception {
	    return intValidator(value);
	}

}