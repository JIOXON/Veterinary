package app.adapters.inputs.utils;

import org.springframework.stereotype.Component;

@Component
public class ownerValidator extends SimpleValidator{

	public String nameValidator(String value) throws Exception {
		return stringValidator(value, "nombre de la persona ");
	}
	
	public long documentValidator(String value)throws Exception {
		return longValidator(value, " numero de documento ");
	}
	
	public int ageValidator(String value) throws Exception {
	    return intValidator(value);
	}
}