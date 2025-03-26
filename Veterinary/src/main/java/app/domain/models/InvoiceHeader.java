package app.domain.models;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InvoiceHeader extends Pet{
    private long invoiceId;
    private Order orderId;
    private String product;
    private double worth;
    private long amount;
    private Date date_Invoice;
    private Person person;
    private Pet petowner;
    private String description;
    
    public long getInvoiceId() {
        return invoiceId;
    }

    public Order getOrderId() {
        return orderId;
    }

    public String getProduct() {
        return product;
    }

    public double getWorth() {
        return worth;
    }

    public long getAmount() {
        return amount;
    }

    public Date getDate_Invoice() {
        return date_Invoice;
    }

    public Person getPerson() {
        return person;
    }

    public Pet getPetowner() {
        return petowner;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setDate_Invoice(Date date_Invoice) {
        this.date_Invoice = date_Invoice;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setPetowner(Pet petowner) {
        this.petowner = petowner;
    }

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public InvoiceHeader() {
    }
}
