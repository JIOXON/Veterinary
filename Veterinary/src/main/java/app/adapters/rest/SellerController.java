package app.adapters.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.adapters.rest.request.SellMedicineRequest;
import app.adapters.rest.request.SellProductRequest;
import app.adapters.rest.response.OrderResponse;
import app.domain.models.Order;
import app.domain.models.Product;
import app.domain.services.SellerService;
import app.ports.OrderPort;

@RestController
@RequestMapping("/seller")
public class SellerController {

	@Autowired
	private SellerService sellerService;

	@Autowired
	private OrderPort orderPort;

	@PostMapping("/products")
	public ResponseEntity<String> sellProduct(@RequestBody SellProductRequest request) {
		try {
			Product product = new Product();
			product.setProductName(request.getProductName());
			product.setPrice(request.getPrice());

			sellerService.sellProduct(product, request.getQuantity());
			return ResponseEntity.ok("Producto vendido exitosamente. Factura generada.");
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/medicines")
	public ResponseEntity<String> sellMedicine(@RequestBody SellMedicineRequest request) {
	    try {
	        // Buscar la orden por ID
	        Order order = orderPort.findByOrderId(request.getOrderId());

	        if (order == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("La orden con ID " + request.getOrderId() + " no existe.");
	        }

	        // Validar que la orden esté vigente
	        if (!"Vigente".equalsIgnoreCase(order.getStatus())) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("La orden con ID " + request.getOrderId() + " no está vigente. Estado actual: " + order.getStatus());
	        }

	        Product medicine = new Product();
	        medicine.setProductName(request.getMedicineName());
	        medicine.setPrice(request.getPrice());

	        sellerService.sellMedicine(request.getOrderId(), medicine, request.getQuantity());

	        return ResponseEntity.ok("Medicamento vendido exitosamente. Factura generada.");
	    } catch (Exception e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@GetMapping("/orders")
	public ResponseEntity<List<OrderResponse>> getAllOrders() {
		List<Order> orders = orderPort.findAllOrders();
		List<OrderResponse> response = orders.stream()
				.map(order -> new OrderResponse(order.getOrderId(), order.getMedicine(), order.getStatus()))
				.collect(Collectors.toList());

		return ResponseEntity.ok(response);
	}
	
}
