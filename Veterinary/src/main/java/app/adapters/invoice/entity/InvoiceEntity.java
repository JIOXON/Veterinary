package app.adapters.invoice.entity;

import java.sql.Date;
import app.domain.models.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "invoice")
public class InvoiceEntity {
	public InvoiceEntity(Invoice invoice) {
		this.invoiceId = invoice.getInvoiceId();
		this.orderId = invoice.getOrderId();
		this.amount = invoice.getAmount();
		this.date_Invoice = invoice.getDate_Invoice();
		this.total_Cost = invoice.getTotal_Cost();
		this.productName = invoice.getProductName();
		this.petId = invoice.getPetId();
		this.petOwnerId = invoice.getPetOwnerId();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "invoiceId")
	private long invoiceId;
	
	@Column (name = "petId")
	private Pet petId;
	
	@Column (name = "petOwnerId")
	private Person petOwnerId;
	
	@Column (name = "orderId")
	private Order orderId;
	
	@Column (name = "productName")
	private Product productName;
	
	@Column (name = "total_Cost")
	private double total_Cost;
	
	@Column (name = "amount")
	private long amount;
	
	@Column (name = "date_Invoice")
	private Date date_Invoice;

	public long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(long invoiceId) {
		this.invoiceId = invoiceId;
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

	public double getTotal_Cost() {
		return total_Cost;
	}

	public void setTotal_Cost(double total_Cost) {
		this.total_Cost = total_Cost;
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

	public InvoiceEntity() {
    }
}
