package app.domain.models;

import java.sql.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Invoice {
    private long invoiceId;
    private Order orderId;
    private Product productName;
    private long amount;
    private Date date_Invoice;
    private double total_Cost;
    private Pet petId;
    private Person petOwnerId;

	public long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Order getOrderId() {
		return orderId;
	}

	public void setOrderId(Order orderId) {
		this.orderId = orderId;
	}

	public Product getProductName() {
		return productName;
	}

	public void setProductName(Product productName) {
		this.productName = productName;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Date getDate_Invoice() {
		return date_Invoice;
	}

	public void setDate_Invoice(Date date_Invoice) {
		this.date_Invoice = date_Invoice;
	}

	public double getTotal_Cost() {
		return total_Cost;
	}

	public void setTotal_Cost(double total_Cost) {
		this.total_Cost = total_Cost;
	}

	public Pet getPetId() {
		return petId;
	}

	public void setPetId(Pet petId) {
		this.petId = petId;
	}

	public Person getPetOwnerId() {
		return petOwnerId;
	}

	public void setPetOwnerId(Person petOwnerId) {
		this.petOwnerId = petOwnerId;
	}

	public Invoice() {
    }
}
