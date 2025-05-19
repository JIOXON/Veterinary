package app.adapters.inputs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.adapters.inputs.utils.Utils;
import app.domain.models.*;
import app.domain.services.SellerService;
import app.ports.InputPort;
import app.ports.OrderPort;

import java.util.List;

@Component
public class SellerInput implements InputPort {

    @Autowired
    private SellerService sellerService;
    
    @Autowired
    private OrderPort orderPort;

    private final String MENU = "Ingrese la opción:"
            + "\n 1. Vender un producto"
            + "\n 2. Vender un medicamento"
            + "\n 3. Consultar ordenes medicas";

    public void menu() throws Exception {
	    boolean running = true;
	    
	    while (running) {  // Bucle para mantener el menú activo
	        System.out.println(MENU);
	        String option = Utils.getReader().nextLine();

	        switch (option) {
	            case "1": {
	                try {
	                	sellProduct();
	                } catch (Exception error) {
	                    System.out.println(error.getMessage());
	                }
	                break;
	            }
	            case "2": {
	                try {
	                	sellMedicine();
	                } catch (Exception error) {
	                    System.out.println(error.getMessage());
	                }
	                break;
	            }
	            case "3": {
	                try {
	                	getAllOrders();
	                } catch (Exception error) {
	                    System.out.println(error.getMessage());
	                }
	                break;
	            }
	            default: {
	                System.out.println("Opción no válida, intente de nuevo.");
	            }
	        }
	    }
	}
    

    private void sellProduct() throws Exception {
        System.out.println("Ingrese el nombre del producto:");
        String productName = Utils.getReader().nextLine();

        System.out.println("Ingrese el precio del producto:");
        double price = Double.parseDouble(Utils.getReader().nextLine());

        System.out.println("Ingrese la cantidad de productos a vender:");
        int quantity = Integer.parseInt(Utils.getReader().nextLine());

        Product product = new Product();
        product.setProductName(productName);
        product.setPrice(price);

        sellerService.sellProduct(product, quantity);
        System.out.println("Producto vendido exitosamente. Factura generada.");
    }

    private void sellMedicine() throws Exception {
        System.out.println("Ingrese el ID de la orden:");
        int orderId = Integer.parseInt(Utils.getReader().nextLine());

        System.out.println("Ingrese el nombre del medicamento:");
        String medicineName = Utils.getReader().nextLine();

        System.out.println("Ingrese el precio del medicamento:");
        double price = Double.parseDouble(Utils.getReader().nextLine());

        System.out.println("Ingrese la cantidad de medicamentos a vender:");
        int quantity = Integer.parseInt(Utils.getReader().nextLine());

        Product medicine = new Product();
        medicine.setProductName(medicineName);
        medicine.setPrice(price);

        sellerService.sellMedicine(orderId, medicine, quantity);
        System.out.println("Medicamento vendido exitosamente. Factura generada.");
    }
    
    public List<Order> getAllOrders() {
        return orderPort.findAllOrders();
    }
}
