package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.domain.models.*;
import app.ports.*;
import java.sql.Date;
import java.util.List;

@Service
public class SellerService {

    @Autowired
    private InvoicePort invoicePort;
    
    @Autowired
    private OrderPort orderPort;
    
    @Autowired
    private ClinicalHistoryPort clinicalHistoryPort;
    
    @Autowired
    private ProductPort productPort;
    
    // Obtener todas las órdenes médicas
    public List<Order> getAllOrders() {
        return orderPort.findAllOrders();
    }

    // Vender Producto y generar factura
    public void sellProduct(Product product, int quantity) throws Exception {
        productPort.sellProduct(product);
        double totalCost = product.getPrice() * quantity;
        // Crear la factura al vender un producto
        generateInvoice(null, product, quantity, totalCost);
    }

    // Vender medicamento, corroborar si tiene orden y generar factura
    public void sellMedicine(int orderId, Product medicine, int quantity) throws Exception {
        if (!orderPort.existOrder(orderId)) {
            throw new Exception("No existe ninguna orden con el ID proporcionado.");
        }

        Order order = orderPort.findByOrderId(orderId);


        if (!"Vigente".equalsIgnoreCase(order.getStatus())) {
        	throw new Exception("No se puede vender el medicamento porque la orden está cancelada.");

        }

        productPort.sellProduct(medicine);


        ClinicalHistory history = new ClinicalHistory();
        String productName = (medicine.getProductName() != null && !medicine.getProductName().isEmpty())
                ? medicine.getProductName()
                : "Medicamento sin nombre especificado";
        history.setDetails("Medicamento vendido: " + productName);
        clinicalHistoryPort.saveClinicalHistory(history);

        double totalCost = medicine.getPrice() * quantity;


        generateInvoice(order, medicine, quantity, totalCost);
    }

    // Generar Factura con la orden si existe
    private void generateInvoice(Order order, Product product, int quantity, double totalCost) {
        Invoice invoice = new Invoice();


        if (order != null) {
            invoice.setOrderId(order.getOrderId());
            invoice.setPetId(order.getPetId());
            invoice.setOwnerId(order.getOwnerId()); 
        }

        invoice.setDate_Invoice(new Date(System.currentTimeMillis()));
        invoice.setTotal_Cost(totalCost);
        invoice.setAmount(quantity); 
        invoicePort.saveInvoice(invoice);
    }

    public SellerService() {
    }
}
