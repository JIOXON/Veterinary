package app.domain.models;

import java.sql.Date;


public class Order{
	private long orderId;
	private String medicine;
	private Date orderGeneration;
	private Pet petId;
	private Person Veterinarian;
	private Person PetOwnerId;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
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

	public Pet getPetId() {
		return petId;
	}

	public void setPetId(Pet petId) {
		this.petId = petId;
	}

	public Person getVeterinarian() {
		return Veterinarian;
	}

	public void setVeterinarian(Person veterinarian) {
		Veterinarian = veterinarian;
	}

	public Person getPetOwnerId() {
		return PetOwnerId;
	}

	public void setPetOwnerId(Person petOwnerId) {
		PetOwnerId = petOwnerId;
	}

	public Order() {
	}
}