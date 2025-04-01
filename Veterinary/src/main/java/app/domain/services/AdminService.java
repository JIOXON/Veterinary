package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.PetOwner;
import app.domain.models.User;
import app.ports.PersonPort;
import app.ports.PetOwnerPort;
import app.ports.PetPort;
import app.ports.UserPort;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private PersonPort personPort;
    @Autowired
    private UserPort userPort;
    @Autowired
    private PetOwnerPort petOwnerPort;
    @Autowired
    private PetPort petPort;
 
    public void registerVeterinarian(User veterinarian)throws Exception{
        if (personPort.existPerson(veterinarian.getDocument())){
            throw new Exception("Ya existe una persona con esa Cedula");
        }
        if (userPort.existUserName(veterinarian.getName())){
            throw new Exception("Ya existe una persona con ese nombre de Usuario");
        }
        personPort.savePerson(veterinarian);
        userPort.saveUser(veterinarian);
    }
    
    public void registerSeller(User seller)throws Exception{
        if (personPort.existPerson(seller.getDocument())){
            throw new Exception("Ya existe una persona con esa Cedula");
        }
        if (userPort.existUserName(seller.getName())){
            throw new Exception("Ya existe una persona con ese nombre de Usuario");
        }
        personPort.savePerson(seller);
        userPort.saveUser(seller);
    }
    
    public void registerUser(User user) throws Exception {
        if (personPort.existPerson(user.getDocument())) {
            throw new Exception("Ya existe una persona con ese Documento");
        }
        if (userPort.existUserId(user.getUserId())) {
            throw new Exception("Ya existe ese usario registrado");
        }
        personPort.savePerson(user);
        userPort.saveUser(user);
    }
    public void registerPet(Pet pet) throws Exception {
        if (petPort.existPetByPetId(pet.getPetId())) {
            throw new Exception("Ya existe una mascota con ese Id");
        }
        petPort.savePet(pet);
    }

    public void registerPetOwner(PetOwner petOwner) throws Exception {
        if (personPort.existPerson(petOwner.getDocument())) {
            throw new Exception("Ya existe una persona con ese Documento");
        }
        personPort.savePerson(petOwner);
    }
       
    public List<User>ListUsers(){
    	return userPort.findAllUsers();
    }
    
    public List<Person> listPersons() {
        return personPort.findAllPersons();
    }
    public List<PetOwner> listOwners() {
        return petOwnerPort.findAllPetOwners();
    }
    
    public AdminService(){
    	
    }
}