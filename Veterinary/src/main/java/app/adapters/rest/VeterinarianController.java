package app.adapters.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import app.Exceptions.BusinessException;
import app.Exceptions.InputsException;
import app.Exceptions.NotFoundException;
import app.adapters.rest.request.OrderRequest;
import app.adapters.rest.request.OwnerRequest;
import app.adapters.rest.request.PetRequest;
import app.adapters.rest.response.ClinicalHistoryResponse;
import app.adapters.rest.response.OrderResponse;
import app.adapters.rest.response.OwnerResponse;
import app.domain.models.ClinicalHistory;
import app.domain.models.Order;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.PetOwner;
import app.domain.services.VeterinarianService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/veterinarian")
public class VeterinarianController {
	@Autowired
    private VeterinarianService veterinarianService;
	
	@PostMapping("/registerOrder")
    public ResponseEntity<String> registerOrder(@RequestBody OrderRequest request) {
        try {
            // Validaciones básicas
            if (request.getProductName() == null || request.getProductName().isEmpty()) {
                throw new InputsException("El nombre del medicamento es obligatorio");
            }
            if (request.getDateOrder() == null || request.getDateOrder().isEmpty()) {
                throw new InputsException("La fecha de la orden es obligatoria");
            }
            if (request.getPetId() <= 0) {
                throw new InputsException("El ID de la mascota es obligatorio");
            }
            if (request.getOwnerId() <= 0) {
                throw new InputsException("El ID del dueño es obligatorio");
            }
            if (request.getUserId() <= 0) {
                throw new InputsException("El ID del usuario es obligatorio");
            }

            // Crear el objeto Order (modelo de dominio)
            Order order = new Order();
            order.setMedicine(request.getProductName());
            order.setOrderGeneration(java.sql.Date.valueOf(LocalDate.parse(request.getDateOrder())));
            order.setPetId(request.getPetId());
            order.setOwnerId(request.getOwnerId());
            order.setUserId(request.getUserId());

            // Llamar al servicio para registrar la orden
            veterinarianService.registerOrder(order);

            return new ResponseEntity<>("Orden registrada exitosamente.", HttpStatus.OK);
        } catch (InputsException ie) {
            return new ResponseEntity<>(ie.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (BusinessException be) {
            return new ResponseEntity<>(be.getMessage(), HttpStatus.CONFLICT);
        } catch (NotFoundException NFe) {
            return new ResponseEntity<>(NFe.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	@GetMapping("/clinicalHistory/{petId}")
    public ResponseEntity<?> getClinicalHistory(@PathVariable long idPet) {
        try {
            if (idPet <= 0) {
                throw new InputsException("El ID de la mascota es obligatorio");
            }
            List<ClinicalHistory> historyList = veterinarianService.getClinicalHistory(idPet);
            if (historyList == null || historyList.isEmpty()) {
                return new ResponseEntity<>("No se encontraron registros de historia clínica para la mascota.", HttpStatus.NOT_FOUND);
            }
            // Mapear a DTO de respuesta
            List<ClinicalHistoryResponse> responseList = historyList.stream()
                .map(h -> new ClinicalHistoryResponse(h.getHistoryId(), h.getPetId(), h.getDetails()))
                .toList();
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        } catch (InputsException ie) {
            return new ResponseEntity<>(ie.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	@GetMapping("/orders")
    public ResponseEntity getAllOrders() {
        try {
            List<Order> orders = veterinarianService.getAllOrders();
            if (orders == null || orders.isEmpty()) {
                return new ResponseEntity<>("No se encontraron órdenes registradas.", HttpStatus.NOT_FOUND);
            }
            List<OrderResponse> responseList = orders.stream()
            		.map(o -> new OrderResponse(
            			    o.getOrderId(),
            			    o.getMedicine(),
            			    o.getOrderGeneration().toLocalDate().toString()
            			))
                .toList();
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@PostMapping("/registerOwner")
	public ResponseEntity<String> registerOwner(@RequestBody OwnerRequest request) {
	    try {
	        if (request.getName() == null || request.getName().isEmpty()) {
	            throw new InputsException("El nombre del dueño es obligatorio");
	        }
	        if (request.getDocument() == null || request.getDocument() <= 0) {
	            throw new InputsException("El documento del dueño es obligatorio");
	        }

	        PetOwner petOwner = new PetOwner();
	        petOwner.setName(request.getName());
	        petOwner.setDocument(request.getDocument());

	        veterinarianService.registerPerson(petOwner);
	        return new ResponseEntity<>("Dueño registrado exitosamente.", HttpStatus.OK);
	    } catch (InputsException ie) {
	        return new ResponseEntity<>(ie.getMessage(), HttpStatus.BAD_REQUEST);
	    } catch (BusinessException be) {
	        return new ResponseEntity<>(be.getMessage(), HttpStatus.CONFLICT);
	    } catch (NotFoundException NFe) {
	        return new ResponseEntity<>(NFe.getMessage(), HttpStatus.NOT_FOUND);
	    } catch (Exception e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

    @GetMapping("/owners")
    public ResponseEntity<?> listOwners() {
        try {
            List<Person> owners = veterinarianService.listOwners();
            if (owners == null || owners.isEmpty()) {
                return new ResponseEntity<>("No se encontraron dueños registrados.", HttpStatus.NOT_FOUND);
            }
            List<OwnerResponse> responseList = owners.stream()
                .map(o -> new OwnerResponse(o.getDocument(), o.getName()))
                .toList();
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/registerPet")
    public ResponseEntity<String> registerPet(@RequestBody PetRequest request) throws Exception {
        try {
            if (request.getName() == null || request.getName().isEmpty()) {
                throw new InputsException("El nombre de la mascota es obligatorio");
            }
            if (request.getOwnerId() == null || request.getOwnerId() <= 0) {
                throw new InputsException("El ID del dueño es obligatorio");
            }
            if (request.getAge() <= 0) {
                throw new InputsException("La edad de la mascota es obligatoria");
            }
            if (request.getSpecies() == null || request.getSpecies().isEmpty()) {
                throw new InputsException("La especie de la mascota es obligatoria");
            }
            if (request.getBreed() == null || request.getBreed().isEmpty()) {
                throw new InputsException("La raza de la mascota es obligatoria");
            }
            if (request.getCharacteristics() == null || request.getCharacteristics().isEmpty()) {
                throw new InputsException("Las características de la mascota son obligatorias");
            }
            if (request.getWeight() <= 0) {
                throw new InputsException("El peso de la mascota es obligatorio");
            }

            Pet pet = new Pet();
            pet.setPetName(request.getName());
            pet.setOwnerId(request.getOwnerId());
            pet.setPetAge(request.getAge());
            pet.setSpecies(request.getSpecies());
            pet.setBreed(request.getBreed());
            pet.setCharacteristics(request.getCharacteristics());
            pet.setWeight(request.getWeight());

            veterinarianService.registerPet(pet);
            return new ResponseEntity<>("Mascota registrada exitosamente.", HttpStatus.OK);
        } catch (InputsException ie) {
            return new ResponseEntity<>(ie.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (BusinessException be) {
            return new ResponseEntity<>(be.getMessage(), HttpStatus.CONFLICT);
        } catch (NotFoundException NFe) {
            return new ResponseEntity<>(NFe.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
