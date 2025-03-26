package app.adapters.inputs;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import app.adapters.inputs.utils.UserValidator;
import app.adapters.inputs.utils.Utils;
import app.domain.models.User;
import app.ports.InputPort;

public class LoginInput implements InputPort{
	
	private Map<String, InputPort> inputs;
	@Autowired
	private AdminInput adminInput;
	@Autowired
	private SellerInput sellerInput;
	@Autowired
	private VeterinarianInput veterinarianInput;
	@Autowired
	private UserValidator userValidator;
	private final String MENU = "Ingrese la opcion que desea:/n 1. Iniciar sesion /n 2. Salir";
	
	public LoginInput(AdminInput adminInput, SellerInput sellerInput, VeterinarianInput veterinarianInput) {
		super();
		this.adminInput = adminInput;
		this.sellerInput = sellerInput;
		this.veterinarianInput = veterinarianInput;
		this.inputs = new HashMap<String,InputPort>();
		inputs.put("admin", adminInput);
		inputs.put("seller", sellerInput);
		inputs.put("veterinarian", veterinarianInput);
	}
	
	@Override
	public void menu() throws Exception {
		System.out.println(MENU);
		String option = Utils.getReader().nextLine();
		switch (option) {
		case "1": {
			this.login();
		}
		case "2": {
			System.out.println("Hasta una proxima ocación");
			return;
		}
		default: {
			System.out.println("ha elegido una opción invalida, se detiene la ejecucion");
			return;
		}
		}
	}

	private void login() {
		try {
			System.out.println("ingrese su usuario");
			String userName = userValidator.userNameValidator(Utils.getReader().nextLine());
			System.out.println("ingrese su contraseña");
			String password = userValidator.passwordValidator(Utils.getReader().nextLine());
			User user = new User();
			InputPort inputPort = inputs.get(user.getRole());
			inputPort.menu();
			/*if(user.getRole()=="admin") {
				admin admin = new admin()
						admin.menu()
			}else if(user.getRole()=="normal") {
				normal normal = new normal()
						normal.menu())
			}else(user.getRole()=="vendedor") {
				vendedor vendedor = new vendedor()
						vendedor.menu()
			}*/
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
