package app.domain.models;

import java.sql.Date;

public class Invoice {
    private Long invoiceId;
    private long orderId;
    private long OwnerId;
    private long petId;
    private long amount;
    private Date date_Invoice;
    private double total_Cost;

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getOwnerId() {
		return OwnerId;
	}

	public void setOwnerId(long ownerId) {
		OwnerId = ownerId;
	}

	public long getPetId() {
		return petId;
	}

	public void setPetId(long petId) {
		this.petId = petId;
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

	public Invoice() {
    }
}
