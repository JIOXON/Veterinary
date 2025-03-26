package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import app.domain.models.InvoiceHeader;
import app.domain.models.Order;
import app.ports.InvoiceHeaderPort;
import app.ports.OrderPort;

public class SellerService {

	@Autowired
	private InvoiceHeaderPort invoiceHeaderPort;
	@Autowired
	private OrderPort orderPort;

	public void InvoiceHeader(InvoiceHeader invoice) throws Exception {
		Order order = orderPort.findById(invoice.getOrderId());
		if(order == null) {
			throw new Exception("meter mensaje de error");
		}
		invoice.setOrderId(order);
		invoice.setPet_id(0);
		invoiceHeaderPort.save(invoice);
	}
}