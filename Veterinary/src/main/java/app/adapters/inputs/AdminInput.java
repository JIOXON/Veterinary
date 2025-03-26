package app.adapters.inputs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.adapters.inputs.utils.PersonValidator;
import app.adapters.inputs.utils.UserValidator;
import app.adapters.inputs.utils.Utils;
import app.domain.models.Person;
import app.domain.models.User;
import app.ports.InputPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class AdminInput implements InputPort{
	@Autowired
	private PersonValidator personValidator;
	@Autowired
	private UserValidator userValidator;

	private final String MENU = "Ingrese la opcion:"
			+ " \n 1. Para Registrar vendedor"
			+ " \n 2. Para Registrar veterinario."
			+ " \n 3. Ver facturas de una persona.";
	
	public void menu() {
		
		System.out.println(MENU);
		String option = Utils.getReader().nextLine();
		switch (option){
		case "1":{
			try {
				this.createVeterinarian();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				break;
			}
			break;
		}
		case "2":{
			try {
				this.createSeller();
		} catch (Exception e){
			System.out.println(e.getMessage());
			break;
			}
			break;
		}
		default :
			System.out.println("opcion no valida");
		}
	}
	
	private void createVeterinarian()  throws Exception{
		System.out.println("ingrese el nombre del veterinario");
		String name = personValidator.nameValidator(Utils.getReader().nextLine());
		System.out.println("ingrese el documento del Veterinario");
		long document = personValidator.documentValidator(Utils.getReader().nextLine());
		System.out.println("ingrese el userName del Veterinario");
		String userName = userValidator.userNameValidator(Utils.getReader().nextLine());
		System.out.println("ingrese la contraseña Veterinario");
		String password = userValidator.passwordValidator(Utils.getReader().nextLine());
		Person person = new Person();
		User user = new User();
		person.setDocument(document);
		person.setName(name);
		user.setUserName(userName);
		user.setPassword(password);
		person.setRole("veterinarian");
	}
	
	private void createSeller()  throws Exception{
		System.out.println("ingrese el nombre del Vendedor");
		String name = personValidator.nameValidator(Utils.getReader().nextLine());
		System.out.println("ingrese el documento del Vendedor");
		long document = personValidator.documentValidator(Utils.getReader().nextLine());
		System.out.println("ingrese el userName del Vendedor");
		String userName = userValidator.userNameValidator(Utils.getReader().nextLine());
		System.out.println("ingrese la contraseña Vendedor");
		String password = userValidator.passwordValidator(Utils.getReader().nextLine());
		Person person = new Person();
		User user = new User();
		person.setDocument(document);
		person.setName(name);
		user.setUserName(userName);
		user.setPassword(password);
		person.setRole("vendedor");
	}
	private void viewInvoice()  throws Exception{
		
	}
}