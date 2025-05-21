package app.domain.services;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.adapters.pet.entity.PetEntity;
import app.domain.models.*;
import app.ports.*;

@Service
public class VeterinarianService {

    @Autowired
    private ClinicalHistoryPort clinicalHistoryPort;

    @Autowired
    private OrderPort orderPort;

    @Autowired
    private PetPort petPort;

    @Autowired
    private PersonPort personPort;

    @Autowired
    private PetOwnerPort petOwnerPort;
    
    @Autowired
    private UserPort userPort;

    public void registerOrder(Order order) throws Exception {
        // Verificar si la mascota asociada existe
        if (!petPort.existPetByPetId(order.getPetId())) {
            throw new Exception("No existe una mascota con el ID especificado.");
        }
    
        // Verificar si el dueño asociado existe
        if (!petOwnerPort.existPetOwner(order.getOwnerId())) {
            throw new Exception("No existe un dueño con la cedula especificada.");
        }
    
        // Verificar si el usuario asociado existe
        if (!userPort.existUserId(order.getUserId())) {
            throw new Exception("No existe un usuario con el ID especificado.");
        }
    
        // Guardar orden en base de datos
        orderPort.saveOrder(order);
    }

    public void cancelOrder(long OrderId, long PetId, String reason) throws Exception {
        if (!orderPort.existOrder(OrderId)) {
            throw new Exception("No existe una orden con el ID especificado");
        }
        orderPort.cancelOrder(OrderId);

        Pet pet = petPort.findPetByPetId(PetId);

        if (pet == null) {
            throw new Exception("No se encontró la mascota con el ID especificado");
        }

        // Registrar la anulación en la historia clínica
        ClinicalHistory clinicalHistory = new ClinicalHistory();
        clinicalHistory.setPetId(PetId);
        clinicalHistory.setDetails("Orden médica anulada. Razón: " + reason);
        clinicalHistoryPort.saveClinicalHistory(clinicalHistory);
    }
    
    // Consultar la historia clinica de una mascota
    public List<ClinicalHistory> getClinicalHistory(long petId) {
        PetEntity entity = new PetEntity();
        entity.setPetId(petId);
        return clinicalHistoryPort.findClinicalHistoryByPetId(entity);
    }

    // Consultar las ordenes de los medicamentos
    public List<Order> getAllOrders() {
        List<Order> orders = orderPort.findAllOrders();
        if (orders == null) {
            return Collections.emptyList(); // Devuelve una lista vacía en lugar de null
        }
        return orders;
    }
    
    public ClinicalHistory createClinicalHistory(ClinicalHistory history) throws Exception {
        // Verificar si la mascota asociada existe
        if (!petPort.existPetByPetId(history.getPetId())) {
            throw new Exception("No existe una mascota con el ID especificado.");
        }
    
        // Crear la historia clínica
        return clinicalHistoryPort.createClinicalHistory(history);
    }

    public void registerPet(Pet pet) throws Exception {
        if (petPort.existPetByPetId(pet.getPetId())) {
            throw new Exception("Ya existe una mascota con ese Id");
        }
       
        petPort.savePet(pet);
    }

    public void registerPetOwner(PetOwner petOwner) throws Exception {
        System.out.println("realiza con datos service " + petOwner.toString());
        if (personPort.existPerson(petOwner.getDocument())) {
            throw new Exception("Ya existe una persona con ese Documento");
        }
        personPort.savePerson(petOwner);
        petOwnerPort.savePetOwner(petOwner);
    }

    public VeterinarianService() {

    }
}
