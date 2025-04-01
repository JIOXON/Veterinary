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

        // Multiplicar el precio por la cantidad
        double totalCost = product.getPrice() * quantity;

        // Crear la factura al vender un producto
        generateInvoice(null, product, quantity, totalCost);
    }

    // Vender medicamento, corroborar si tiene orden y generar factura
    public void sellMedicine(int orderId, Product medicine, int quantity) throws Exception {
        if (!orderPort.existOrder(orderId)) {
            throw new Exception("No existe ninguna orden");
        }

        productPort.sellProduct(medicine);
        Order order = orderPort.findByOrderId(orderId);

        // Registrar la venta en la historia clínica
        ClinicalHistory history = new ClinicalHistory();
        history.setDetails("Medicamento vendido: " + medicine.getProductName());
        clinicalHistoryPort.saveClinicalHistory(history);

        // Multiplicar el precio por la cantidad
        double totalCost = medicine.getPrice() * quantity;

        // Crear la factura al vender un medicamento
        generateInvoice(order, medicine, quantity, totalCost);
    }

    // Generar Factura con la orden si existe
    private void generateInvoice(Order order, Product product, int quantity, double totalCost) {
        Invoice invoice = new Invoice();


        if (order != null) {
            invoice.setOrderId(order.getOrderId());
            invoice.setPetId(order.getPetId());
            //invoice.setPetOwnerId(order.getPet().getOwner().getOwnerId()); 
        }

        invoice.setDate_Invoice(new Date(System.currentTimeMillis()));
        invoice.setTotal_Cost(totalCost);
        invoice.setAmount(quantity); 

        invoicePort.saveInvoice(invoice);
    }


    // Obtener todas las facturas generadas
    public List<Invoice> getAllInvoices() {
        return invoicePort.getAllInvoices();
    }

    public SellerService() {
    }
}
