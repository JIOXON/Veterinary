package app.domain.models;

import java.sql.Date;

public class Order{
	private long orderId;
	private long ownerId;
	private long petId;
    private long userId;
	private String medicineName;
	private Date orderGeneration;
	private String status;
	private String cancellationReason;
	
	 

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCancellationReason() {
		return cancellationReason;
	}

	public void setCancellationReason(String cancellationReason) {
		this.cancellationReason = cancellationReason;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public long getPetId() {
		return petId;
	}

	public void setPetId(long petId) {
		this.petId = petId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getMedicine() {
		return medicineName;
	}

	public void setMedicine(String medicine) {
		this.medicineName = medicine;
	}

	public Date getOrderGeneration() {
		return orderGeneration;
	}

	public void setOrderGeneration(Date orderGeneration) {
		this.orderGeneration = orderGeneration;
	}

	public Order() {
	}
	
}