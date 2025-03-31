package app.adapters.invoice.entity;

import java.sql.Date;
import app.adapters.order.entity.OrderEntity;
import app.adapters.pet.entity.PetEntity;
import app.adapters.petOwner.entity.PetOwnerEntity;
import app.domain.models.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "invoice")
public class InvoiceEntity {
	public InvoiceEntity(Invoice invoice, OrderEntity orderEntity, PetEntity petEntity, PetOwnerEntity petOwnerEntity) {
	    this.invoiceId = (invoice.getInvoiceId() != null) ? invoice.getInvoiceId() : 0;
	    this.orderId = orderEntity;
	    this.petId = petEntity;
	    this.OwnerId = petOwnerEntity;
	    this.amount = invoice.getAmount();
	    this.date_Invoice = invoice.getDate_Invoice();
	    this.total_Cost = invoice.getTotal_Cost();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "invoice_id")
	private long invoiceId;
	
    @JoinColumn(name = "pet_id")
    @OneToOne
	private PetEntity petId;
	
    @JoinColumn (name = "owner_id")
	@OneToOne
	private PetOwnerEntity OwnerId;
	
    @JoinColumn (name = "order_id")
    @OneToOne
	private OrderEntity orderId;
	
	@Column (name = "total_cost")
	private double total_Cost;
	
	@Column (name = "amount")
	private long amount;
	
	@Column (name = "date_invoice")
	private Date date_Invoice;
	
	public long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public PetEntity getPetId() {
		return petId;
	}

	public void setPetId(PetEntity petId) {
		this.petId = petId;
	}

	public PetOwnerEntity getOwnerId() {
		return OwnerId;
	}

	public void setOwnerId(PetOwnerEntity ownerId) {
		OwnerId = ownerId;
	}

	public OrderEntity getOrderId() {
		return orderId;
	}

	public void setOrderId(OrderEntity orderId) {
		this.orderId = orderId;
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
