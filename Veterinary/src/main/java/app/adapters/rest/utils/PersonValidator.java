package app.adapters.rest.utils;

import org.springframework.stereotype.Component;

@Component
public class PersonValidator extends SimpleValidator{
	
	public String nameValidator(String value) throws Exception {
		return stringValidator(value, "nombre de la persona ");
	}
	
	public long documentValidator(String value)throws Exception {
		return longValidator(value, " numero de documento ");
	}
	
	public String roleValidator(String value)throws Exception {
		return stringValidator(value, "Que rol cumple ");
	}
	public int ageValidator(String value) throws Exception {
	    return intValidator(value);
	}
}