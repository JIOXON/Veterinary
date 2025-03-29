package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import app.domain.models.Person;
import app.domain.models.User;
import app.ports.PersonPort;
import app.ports.UserPort;
import java.util.List;

public class AdminService {
    @Autowired
    private PersonPort personPort;
    @Autowired
    private UserPort userPort;
 
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
    public void registerPetOwner(Person person) throws Exception {
        if (personPort.existPerson(person.getDocument())) {
            throw new Exception("Ya existe una persona con ese Documento");
        }
        personPort.savePerson (person);
    }
       
    public List<User>ListUsers(){
    	return userPort.findAllUsers();
    }
    
    public List<Person> listOwners() {
        return personPort.findAllPersons();
    }
}