package app.adapters.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.adapters.rest.request.CreateUserRequest;
import app.adapters.rest.response.PersonResponse;
import app.adapters.rest.response.UserResponse;
import app.domain.models.Person;
import app.domain.models.User;
import app.domain.services.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest request) {
       try {
    	User user = new User();
        user.setName(request.getName());
        user.setDocument(request.getDocument());
        user.setAge(request.getAge());
        user.setUserName(request.getUserName());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        adminService.registerUser(user);
        return ResponseEntity.ok("Usuario creado exitosamente.");}
       catch(Exception e ) {
    	   return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

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

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> listUsers() {
        List<User> users = adminService.ListUsers();
        List<UserResponse> response = users.stream()
            .map(u -> new UserResponse(u.getUserId(), u.getName(), u.getRole()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
