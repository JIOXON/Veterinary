package app.domain.services;

import app.domain.models.User;
import app.ports.PersonPort;
import app.ports.UserPort;

public class AdminService {
    
    private PersonPort personPort;
    private UserPort userPort;
 
    public void registerVeterinarian(User veterinarian)throws Exception{
        if (personPort.existPerson(veterinarian.getDocument())){
            throw new Exception("Ya existe una persona con esa Cedula");
        }
        if (userPort.existUserName(veterinarian.getName())){
            throw new Exception("Ya existe una persona con ese nombre de usuario");
        }
        personPort.savePerson(veterinarian);
        userPort.saveUser(veterinarian);
    }
    public void registerSeller(User seller)throws Exception{
        if (personPort.existPerson(seller.getDocument())){
            throw new Exception("Ya existe una persona con esa Cedula");
        }
        if (userPort.existUserName(seller.getName())){
            throw new Exception("Ya existe una persona con ese nombre de usuario");
        }
        personPort.savePerson(seller);
        userPort.saveUser(seller);
    }
}