package app.adapters.inputs;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.adapters.inputs.utils.*;
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
    @Autowired
    private PetValidator petValidator;
    

	private final String MENU = "Ingrese la opcion:"
            + " \n 1. Crear Usuario."
            + " \n 2. Registrar Dueño de mascota y mascota"
            + " \n 3. Lista de personas registradas"
            + " \n 4. Lista de Usuarios"
			+ " \n 5. Salir de la aplicacion";
	
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
	                    registerPet();
	                } catch (Exception error) {
	                    System.out.println(error.getMessage());
	                }
	                break;
	            }
	            case "3": {
	                try {
	                    listPersons();
	                } catch (Exception error) {
	                    System.out.println(error.getMessage());
	                }
	                break;
	            }
	            case "4": {
	                try {
	                    listUsers();
	                } catch (Exception error) {
	                    System.out.println(error.getMessage());
	                }
	                break;
	            }
	            case "5": {  // Opción para salir
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
	
	private void registerPet() {
        try {
            // Recolectamos los datos para la mascota
            System.out.println("Ingrese nombre de la mascota: ");
            String name = petValidator.nameValidator(Utils.getReader().nextLine());
            
            System.out.println("Ingrese la edad de la mascota: ");
            int age = petValidator.ageValidator(Utils.getReader().nextLine());
            
            System.out.println("Ingrese la raza de la mascota: ");
            String breed = petValidator.nameValidator(Utils.getReader().nextLine());
            
            System.out.println("Ingrese características de la mascota: ");
            String characteristics = petValidator.nameValidator(Utils.getReader().nextLine());
            
            System.out.println("Ingrese la especie de la mascota: ");
            String species = petValidator.nameValidator(Utils.getReader().nextLine());
            
            System.out.println("Ingrese el peso de la mascota: ");
            int weight = SimpleValidator.intValidator(Utils.getReader().nextLine());
            
            // Primero registramos al dueño de la mascota
            System.out.println("Ingrese nombre del dueño de la mascota: ");
            String ownerName = ownerValidator.nameValidator(Utils.getReader().nextLine());
            
            System.out.println("Ingrese la edad del dueño de la mascota: ");
            int ownerAge = ownerValidator.ageValidator(Utils.getReader().nextLine());
            
            System.out.println("Ingrese el documento del dueño de la mascota: ");
            Long ownerDocument = ownerValidator.documentValidator(Utils.getReader().nextLine());

            PetOwner petOwner = new PetOwner();
            petOwner.setName(ownerName);
            petOwner.setAge(ownerAge);
            petOwner.setDocument(ownerDocument);

            // Registramos al dueño en el servicio
            adminService.registerPetOwner(petOwner);
            
            // Ahora registramos la mascota
            Pet pet = new Pet();
            pet.setPetName(name);
            pet.setBreed(breed);
            pet.setPetAge(age);
            pet.setCharacteristics(characteristics);
            pet.setSpecies(species);
            pet.setWeight(weight);
            pet.setOwnerId(ownerDocument); // Asignamos el documento del dueño como ID

            adminService.registerPet(pet);
            System.out.println("Mascota registrada correctamente.");
        } catch (Exception error) {
            System.out.println("Error de Registro: " + error.getMessage());
        }
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