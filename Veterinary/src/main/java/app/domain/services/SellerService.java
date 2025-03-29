package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import app.domain.models.ClinicalHistory;
import app.domain.models.*;
import app.ports.*;
import java.sql.Date;
import java.util.List;

public class SellerService {

	@Autowired
	private InvoicePort invoicePort;
	@Autowired
	private OrderPort orderPort;
	@Autowired
	private ClinicalHistoryPort clinicalHistory;
	@Autowired
	private ProductPort productPort;
	
	//Ordenes Medicas
	public List<Order> getAllOrders(){
		return orderPort.findAllOrders();
	}
	
	//Vender Producto
	public void sellProduct(Product product) throws Exception{
		productPort.sellProduct(product);
		generateInvoice(null, product);
	}
	
	//Vender medicamento, corroborar si tiene orden
    public void sellMedicine(int orderId, Product medicine) throws Exception {
        //Existencia de orden
        if (!orderPort.existOrder(orderId)) {
            throw new Exception("No existe ninguna orden");
        }
        //Vender
        productPort.sellProduct(medicine);

        // Registrar la venta en la historia clínica
        Order order = orderPort.findById(orderId);
        ClinicalHistoryPort clinical = new ClinicalHistoryPort();
        clinical.setIdPet(order.getIdPet());
        clinical.setDetails("Medicamento vendido: " + medicine.getProductName());
        clinicalHistory.saveClinicalHistoryId(clinicalHistory);

        //Factura
        generateInvoice(order, medicine);
    }
	
	//Generar Factura
	private void generateInvoice(Order order, Product product) {
		Invoice invoice = new Invoice();
		if (order != null) {
			invoice.setOrderId(invoice.getOrderId());
			invoice.setPetId(invoice.getPetId());
			invoice.setPetOwnerId(invoice.getPetOwnerId());
		}
		invoice.setDate_Invoice(new Date(System.currentTimeMillis())); //Ejemplo de salida: 2025-03-28 (formato YYYY-MM-DD).
		invoice.setTotal_Cost(product.getPrice());
		invoicePort.saveInvoice(invoice);
	}
}