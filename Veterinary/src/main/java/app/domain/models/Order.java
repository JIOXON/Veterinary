package app.domain.models;

import java.sql.Date;

public class Order{
	private long orderId;
	private long ownerId;
	private long petId;
    private long userId;
	private String medicine;
	private Date orderGeneration;

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
		return medicine;
	}

	public void setMedicine(String medicine) {
		this.medicine = medicine;
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