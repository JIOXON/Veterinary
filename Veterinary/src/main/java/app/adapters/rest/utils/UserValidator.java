package app.adapters.rest.utils;

import org.springframework.stereotype.Component;

@Component
public class UserValidator extends SimpleValidator{

	public static String userNameValidator(String value) throws Exception{
		return stringValidator(value, "Nombre del usuario ");
	}
	public static String passwordValidator(String value) throws Exception{
		return stringValidator(value, " contraseña del usuario ");
	}
}