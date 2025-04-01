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


    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll().stream().map(entity -> {
            Invoice invoice = new Invoice();
            invoice.setInvoiceId(entity.getInvoiceId());
            invoice.setOrderId(entity.getOrderId() != null ? entity.getOrderId().getOrderId() : 0);
            invoice.setPetId(entity.getPetId() != null ? entity.getPetId().getPetId() : 0);
            invoice.setOwnerId(entity.getOwnerId() != null ? entity.getOwnerId().getOwnerId() : 0);
            invoice.setTotal_Cost(entity.getTotal_Cost());
            invoice.setAmount(entity.getAmount());
            invoice.setDate_Invoice(entity.getDate_Invoice());
            return invoice;
        }).collect(Collectors.toList());
    }

}
