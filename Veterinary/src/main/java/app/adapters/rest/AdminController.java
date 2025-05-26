package app.adapters.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import app.Exceptions.BusinessException;
import app.Exceptions.InputsException;
import app.Exceptions.NotFoundException;
import app.adapters.rest.request.CreateUserRequest;
import app.adapters.rest.request.UserRequest;
import app.adapters.rest.response.PersonResponse;
import app.adapters.rest.response.UserResponse;
import app.adapters.rest.utils.PersonValidator;
import app.adapters.rest.utils.UserValidator;
import app.domain.models.Person;
import app.domain.models.User;
import app.domain.services.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    
    @Autowired
    private PersonValidator personValidator;

    @Autowired
	private UserValidator userValidator;
    
    public String itsAlive() {
		return "i'm alive";
	}
	
	@GetMapping("/ping")
	public String ping() {
		return "pong";
	}
    
	@PostMapping("/user")
    public ResponseEntity createUser(@RequestBody UserRequest request){
        try {
            System.out.println("Request: " + request.toString());
            User user = new User();
            user.setName(personValidator.nameValidator(request.getName()));
            if(request.getDocument()==0) {
                throw new InputsException("el numero de documento no puede ser cero");
            }
            user.setDocument(personValidator.documentValidator(String.valueOf(request.getDocument())));
            if(request.getAge()==0) {
                throw new InputsException("la edad no puede ser cero");
            }
            user.setAge((request.getAge()));
            user.setUserName(userValidator.userNameValidator(request.getUserName()));
		    user.setPassword(userValidator.passwordValidator(request.getPassword()));
            user.setRole((request.getRole()));
            adminService.registerUser(user);
            return new ResponseEntity("se ha creado el socio",HttpStatus.OK);
        }catch(BusinessException be) {
			return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
		}catch(InputsException ie) {
			return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
		}catch (NotFoundException NFe) {
            return new ResponseEntity<>(NFe.getMessage(), HttpStatus.NOT_FOUND);
        }catch(Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	@GetMapping("/user")
    public ResponseEntity getUser() {
        try{
            List<User> users = adminService.getUsers();
            List<UserResponse> userResponse  = new ArrayList<UserResponse>();
            for (User user : users) {
                userResponse.add(adapter(user));
            }
            return new ResponseEntity(userResponse,HttpStatus.OK);
        }catch(NotFoundException NFe) {
			return new ResponseEntity(NFe.getMessage(),HttpStatus.NOT_FOUND);
        
        }catch(BusinessException be) {
			return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
          
    }

    @GetMapping("/persons")
    public ResponseEntity<List<PersonResponse>> listPersons() {
        List<Person> persons = adminService.listPersons();
        List<PersonResponse> response = persons.stream()
            .map(p -> new PersonResponse(p.getDocument(), p.getName()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    
    private UserResponse adapter(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setName(user.getUserName());
        userResponse.setDocument(user.getDocument());
        userResponse.setRole(user.getRole());
        return userResponse;
    }

}
