package app.adapters.inputs;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapters.rest.utils.PetValidator;
import app.adapters.rest.utils.SimpleValidator;
import app.adapters.rest.utils.Utils;
import app.adapters.rest.utils.ownerValidator;
import app.domain.models.ClinicalHistory;
import app.domain.models.Order;
import app.domain.models.Pet;
import app.domain.models.PetOwner;
import app.domain.services.VeterinarianService;
import app.ports.InputPort;

@Component
public class VeterinarianInput implements InputPort{
	
	@Autowired
    private VeterinarianService veterinarianService;
    
	@Autowired
    private PetValidator petValidator;
	
	@Autowired
    private ownerValidator ownerValidator;
	
	private final String MENU = "Ingrese la opción:"
	        + " \n 1. Registrar dueño y su mascota"
			+ " \n 2. Registrar una orden"
	        + " \n 3. Cancelar una orden"
			+ " \n 4. Consultar Ordenes"
	        + " \n 5. Registrar historia clinica"
	        + " \n 6. Cosultar historia clinica"
	        + " \n 7. Cerrar sesión";
	
	public void menu() {
		boolean running = true;
		
		while (running) { 
			
			System.out.println(MENU);
			String option = Utils.getReader().nextLine();
			
			switch (option) {
            	case "1": {
            		try {
            			registerPet();
            		} catch (Exception e) {
            			System.out.println("Error: " + e.getMessage());
            		}
            		break;
            	}
            	case "2": {
            		try {
            			registerOrder();
            		} catch (Exception e) {
            			System.out.println("Error: " + e.getMessage());
            		}
            		break;
            	}
            	case "3": {
            		try {
            			cancelOrder();
            		} catch (Exception e) {
            			System.out.println("Error: " + e.getMessage());
            		}
            		break;
            	}
            	case "4": {
            		try {
            			getAllOrders();
            		} catch (Exception e) {
            			System.out.println("Error: " + e.getMessage());
            		}
            		break;
            	}
            	case "5": {
            		try {
            			setClinicalHistory();
            		} catch (Exception e) {
            			System.out.println("Error: " + e.getMessage());
            		}
            		break;
            	}
            	case "6": {
            		try {
            			getClinicalHistory();
            		} catch (Exception e) {
            			System.out.println("Error: " + e.getMessage());
            		}
            		break;
            	}
            	case "7": {  // Opción para salir
	                System.out.println("Saliendo del menú de veterinario...");
	                running = false;
	                break;
	            }
            	default: {
            		System.out.println("Opción no válida");
            		break;
            	}
			}
		}
	}
	
	private void registerOrder() throws Exception {
	    System.out.println("Ingrese el nombre del medicamento:");
	    String medicine = ownerValidator.nameValidator(Utils.getReader().nextLine());
	    
	    System.out.println("Ingrese el ID del veterinario:");
	    long veterinarianId = Long.parseLong(Utils.getReader().nextLine());
	    
	    System.out.println("Ingrese el ID de la mascota:");
	    long petId = Long.parseLong(Utils.getReader().nextLine());
	    
	    System.out.println("Ingrese la cedula del dueño:");
	    long OwnerId = Long.parseLong(Utils.getReader().nextLine());

	    // Obtener la fecha de generación actual
	    Date orderGeneration = new Date(System.currentTimeMillis());

	    // Crear y llenar el objeto Order
	    Order order = new Order();
	    order.setMedicine(medicine);
	    order.setOrderGeneration(orderGeneration);
	    order.setUserId(veterinarianId); // El ID del veterinario
	    order.setPetId(petId);
	    order.setOwnerId(OwnerId);

	    veterinarianService.registerOrder(order);
	    System.out.println("Orden registrada exitosamente.");
	}


    private void cancelOrder() throws Exception {
        System.out.println("Ingrese el ID de la orden a cancelar:");
        int orderId = Integer.parseInt(Utils.getReader().nextLine());
        
        System.out.println("Ingrese el ID de la mascota a cancelar:");
        long petId = Long.parseLong(Utils.getReader().nextLine());
        
        System.out.println("Ingrese la razon de cancelar:");
        String reason = Utils.getReader().nextLine();
    
        veterinarianService.cancelOrder(orderId, petId,reason);
        System.out.println("Orden cancelada exitosamente.");
    }

    private void getClinicalHistory() {
        try {
            System.out.println("Ingrese el ID de la mascota:");
            long petId = Long.parseLong(Utils.getReader().nextLine());

            List<ClinicalHistory> history = veterinarianService.getClinicalHistory(petId);
            System.out.println("Historia clínica de la mascota:");
            for (ClinicalHistory record : history) {
                System.out.println("ID Historia: " + record.getHistoryId());
                System.out.println("ID Mascota: " + record.getPetId());
                System.out.println("Descripción: " + record.getDetails());
                System.out.println("-----------------------------");
            }
        } catch (Exception error) {
            System.out.println("Error al consultar la historia clínica: " + error.getMessage());
        }
    }
    
    private void setClinicalHistory() {
        try {
            System.out.println("Ingrese el ID de la mascota:");
            long petId = Long.parseLong(Utils.getReader().nextLine());
            
            System.out.println("Ingrese los detalles de la historia clínica:");
            String details = Utils.getReader().nextLine();

            // Crear objeto ClinicalHistory
            ClinicalHistory clinicalHistory = new ClinicalHistory();
            clinicalHistory.setPetId(petId);
            clinicalHistory.setDetails(details);

            // Guardar la historia clínica en el servicio
            veterinarianService.createClinicalHistory(clinicalHistory);

            System.out.println("Historia clínica registrada exitosamente.");
        } catch (Exception error) {
            System.out.println("Error al registrar la historia clínica: " + error.getMessage());
        }
    }


    private void getAllOrders() {
        try {
            List<Order> orders = veterinarianService.getAllOrders();
            if (orders.isEmpty()) {
                System.out.println("No hay órdenes registradas.");
            } else {
                System.out.println("Lista de órdenes:");
                for (Order order : orders) {
                    System.out.println("--------------------------------------------------");
                    System.out.println("ID de Orden: " + order.getOrderId());
                    System.out.println("Medicamento: " + order.getMedicine());
                    System.out.println("Fecha de Generación: " + order.getOrderGeneration());
                    System.out.println("Estado: " + order.getStatus());
                    System.out.println("Motivo de Cancelación: " + order.getCancellationReason());
                    System.out.println("ID del Dueño: " + order.getOwnerId());
                    System.out.println("ID de la Mascota: " + order.getPetId());
                    System.out.println("ID del Veterinario: " + order.getUserId());
                    System.out.println("--------------------------------------------------");
                }
            }
        } catch (Exception error) {
            error.printStackTrace(); // Muestra el error en detalle en la consola
            System.out.println("Error al consultar las órdenes: " + error.getMessage());
        }
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
            veterinarianService.registerPerson(petOwner);
            
            // Ahora registramos la mascota
            Pet pet = new Pet();
            pet.setPetName(name);
            pet.setBreed(breed);
            pet.setPetAge(age);
            pet.setCharacteristics(characteristics);
            pet.setSpecies(species);
            pet.setWeight(weight);
            pet.setOwnerId(ownerDocument); // Asignamos el documento del dueño como ID

            veterinarianService.registerPet(pet);
            System.out.println("Mascota registrada correctamente.");
        } catch (Exception error) {
            System.out.println("Error de Registro: " + error.getMessage());
        }
    }



}
