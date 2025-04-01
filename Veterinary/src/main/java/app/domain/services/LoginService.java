package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.domain.models.User;
import app.ports.UserPort;


@Service
public class LoginService {

	@Autowired
    private UserPort userPort;
	
	public User login(User user) throws Exception {
        User userValidate = userPort.findByUserName(user.getUserName());
        
        if (userValidate == null) {
            throw new Exception("Usuario o contrasena inconrrecto");
        }

        if (!user.getPassword().equals(userValidate.getPassword())) {
            throw new Exception("Usuario o contrasena inconrrecto");
        }
        return userValidate;
    }
	public LoginService () {
	}
}