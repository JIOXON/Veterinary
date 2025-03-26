package app.ports;

import app.domain.models.InvoiceHeader;
import app.domain.models.Person;
import java.util.List;

public interface InvoiceHeaderPort {
	public List<InvoiceHeader> getAllInvoices();

    public List<InvoiceHeader> getInvoicesByPerson(Person person);

	public void save(InvoiceHeader invoiceHeader);
}
