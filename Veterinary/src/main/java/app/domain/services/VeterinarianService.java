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

    public void registerOrder(Order order) throws Exception {
        if (orderPort.existOrder(order.getOrderId())) {
            throw new Exception("Ya existe una orden con el ID especificado");
        }
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

    public void saveClinicalHistory(ClinicalHistory clinicalHistory) throws Exception {
        if (clinicalHistory == null) {
            throw new Exception("La historia clínica no puede ser nula.");
        }

        // Verificar si la mascota existe en la base de datos
        Pet pet = petPort.findPetByPetId(clinicalHistory.getPetId());
        if (pet == null) {
            throw new Exception("No se encontró la mascota con el ID especificado.");
        }

        // Verificar si la mascota tiene un dueño asignado
        if (pet.getOwnerId() == null) {
            throw new Exception("La mascota no tiene un dueño asignado.");
        }

        // Guardar la historia clínica
        clinicalHistoryPort.saveClinicalHistory(clinicalHistory);
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
