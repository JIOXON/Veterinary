package app.adapters.inputs;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapters.rest.utils.PersonValidator;
import app.adapters.rest.utils.UserValidator;
import app.adapters.rest.utils.Utils;
import app.domain.models.User;
import app.domain.services.LoginService;
import app.ports.InputPort;

@Component
public class LoginInput implements InputPort{
	
	private Map<String, InputPort> inputs;
	@Autowired
	private AdminInput adminInput;
	@Autowired
	private SellerInput sellerInput;
	@Autowired
	private VeterinarianInput veterinarianInput;
	@Autowired
	private PersonValidator personValidator;
	@Autowired
    private LoginService loginService;
	private final String MENU = "Ingrese la opcion que desea:\n 1. iniciar sesion \n 2. salir";
	
	public LoginInput(AdminInput adminInput, SellerInput sellerInput, VeterinarianInput veterinarianInput) {
		super();
		this.adminInput = adminInput;
		this.sellerInput = sellerInput;
		this.veterinarianInput = veterinarianInput;
		this.inputs = new HashMap<String,InputPort>();
		inputs.put("Admin", adminInput);
		inputs.put("Seller", sellerInput);
		inputs.put("Veterinarian", veterinarianInput);
	}
	
	@Override
	public void menu() throws Exception {
	    boolean running = true;

	    while (running) {
	        System.out.println(MENU);

	        String option = Utils.getReader().nextLine();
	        switch (option) {
	            case "1": {
	                this.login();
	                break;
	            }
	            case "2": {
	                System.out.println("Hasta una próxima ocasión");
	                running = false;
	                break;
	            }
	            default: {
	                System.out.println("Ha elegido una opción inválida, intente nuevamente.");
	                break;
	            }
	        }
	    }
	}

	private void login() {
	    try {
	        User user = null;


	        while (user == null) {
	            System.out.println("Ingrese su usuario:");
	            String userName = UserValidator.userNameValidator(Utils.getReader().nextLine());

	            System.out.println("Ingrese su contraseña:");
	            String password = UserValidator.passwordValidator(Utils.getReader().nextLine());


	            user = new User();
	            user.setUserName(userName);
	            user.setPassword(password);
	            

	            user = loginService.login(user);


	            if (user == null) {
	                System.out.println("❌ Usuario o contraseña incorrectos. Intente nuevamente.\n");
	            }
	        }


	        System.out.println("✅ Usuario autenticado. Rol: " + user.getRole());

	        // Buscamos el rol en el mapa
	        InputPort inputPort = inputs.get(user.getRole());

	        // ❌ Si el rol no está en el mapa, mostramos un mensaje de error y terminamos
	        if (inputPort == null) {
	            System.out.println("❌ Error: Rol '" + user.getRole() + "' no reconocido. Contacte al administrador.");
	            return;
	        }

	        // ✅ Si todo está correcto, entramos al menú del rol correspondiente
	        inputPort.menu();

	    } catch (Exception error) {
	        System.out.println("❌ Error en el inicio de sesión: " + error.getMessage());
	        error.printStackTrace(); // Esto mostrará más detalles del error en consola
	    }
	}


	public Map<String, InputPort> getInputs() {
		return inputs;
	}

	public void setInputs(Map<String, InputPort> inputs) {
		this.inputs = inputs;
	}

	public AdminInput getAdminInput() {
		return adminInput;
	}

	public void setAdminInput(AdminInput adminInput) {
		this.adminInput = adminInput;
	}

	public SellerInput getSellerInput() {
		return sellerInput;
	}

	public void setSellerInput(SellerInput sellerInput) {
		this.sellerInput = sellerInput;
	}

	public VeterinarianInput getVeterinarianInput() {
		return veterinarianInput;
	}

	public void setVeterinarianInput(VeterinarianInput veterinarianInput) {
		this.veterinarianInput = veterinarianInput;
	}

	public PersonValidator getPersonValidator() {
		return personValidator;
	}

	public void setPersonValidator(PersonValidator personValidator) {
		this.personValidator = personValidator;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public String getMENU() {
		return MENU;
	}
	
}
