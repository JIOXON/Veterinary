package app.adapters.inputs;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapters.rest.utils.*;
import app.domain.models.*;
import app.domain.services.AdminService;
import app.ports.InputPort;

@Component
public class AdminInput implements InputPort{
	@Autowired
    private PersonValidator personValidator;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ownerValidator ownerValidator;
    

	private final String MENU = "Ingrese la opcion:"
            + " \n 1. Crear Usuario."
            + " \n 2. Lista de personas registradas"
            + " \n 3. Lista de Usuarios"
            + " \n 4. Cerrar Sesión";
	
	public void menu() throws Exception {
	    boolean running = true;
	    
	    while (running) {  // Bucle para mantener el menú activo
	        System.out.println(MENU);
	        String option = Utils.getReader().nextLine();

	        switch (option) {
	            case "1": {
	                try {
	                    createUser();
	                } catch (Exception error) {
	                    System.out.println(error.getMessage());
	                }
	                break;
	            }
	            case "2": {
	                try {
	                    listPersons();
	                } catch (Exception error) {
	                    System.out.println(error.getMessage());
	                }
	                break;
	            }
	            case "3": {
	                try {
	                    listUsers();
	                } catch (Exception error) {
	                    System.out.println(error.getMessage());
	                }
	                break;
	            }
	            case "4": {  // Opción para salir
	                System.out.println("Saliendo del menú de administrador...");
	                running = false;
	                break;
	            }

	            default: {
	                System.out.println("Opción no válida, intente de nuevo.");
	            }
	        }
	    }
	}
	
	private void createUser() throws Exception {
		
        System.out.println("Ingrese el nombre de la persona:");
        String name = personValidator.nameValidator(Utils.getReader().nextLine());
        
        System.out.println("Ingrese el documento del usuario:");
        long document = personValidator.documentValidator(Utils.getReader().nextLine());
        
        System.out.println("Ingrese la edad de la persona: ");
        int age = ownerValidator.ageValidator(Utils.getReader().nextLine());
        
        System.out.println("Ingrese el usuario de la persona:");
        String userName = UserValidator.userNameValidator(Utils.getReader().nextLine());
    
        System.out.println("Ingrese la contraseña del usuario");
        String password = Utils.getReader().nextLine();

     // Selección de rol
	    String role = "";
	    while (true) {
	        System.out.println("Seleccione el rol del usuario:");
	        System.out.println("1. Veterinario");
	        System.out.println("2. Vendedor");
	        String option = Utils.getReader().nextLine();

	        if (option.equals("1")) {
	            role = "Veterinarian";
	            break;
	        } else if (option.equals("2")) {
	            role = "Seller";
	            break;
	        } else {
	            System.out.println("Opción inválida. Ingrese 1 para veterinario o 2 para vendedor.");
	            return;
	        }
	    }
	    User user = new User();
        user.setDocument(document);
        user.setName(name);
        user.setUserName(userName);
        user.setAge(age);
        user.setRole(role);
        user.setPassword(password);
        adminService.registerUser(user);
    }
	
	private void listPersons() {
        try {
            System.out.println("Lista de personas asociadas:");
            List<Person> owners = adminService.listPersons();
            for (Person owner : owners) {
                System.out.println("Documento: " + owner.getDocument() + " -- Nombre: " + owner.getName());
            }
        } catch (Exception error) {
            System.out.println("Error al listar los personas: " + error.getMessage());
        }
    }
	
	private void listUsers() {
        try {
            System.out.println("Lista de usuarios:");
            List<User> users = adminService.ListUsers();
            for (User user : users) {
                System.out.println("ID: " + user.getUserId() + " -- Nombre: " + user.getName() + " -- Rol: " + user.getRole());
            }
        } catch (Exception error) {
            System.out.println("Error al listar los usuarios: " + error.getMessage());
        }
    }
}