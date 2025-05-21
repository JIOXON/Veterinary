package app.adapters.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.adapters.invoice.entity.InvoiceEntity;
import app.adapters.invoice.repository.InvoiceRepository;
import app.domain.models.Invoice;
import app.ports.InvoicePort;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceAdapter implements InvoicePort {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public void saveInvoice(Invoice invoice) {
        InvoiceEntity invoiceEntity = new InvoiceEntity(invoice, null, null, null); 
        invoiceRepository.save(invoiceEntity);
        invoice.setInvoiceId(invoiceEntity.getInvoiceId());
    }
}
