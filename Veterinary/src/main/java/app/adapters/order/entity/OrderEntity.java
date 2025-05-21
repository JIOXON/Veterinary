package app.adapters.order.entity;

import java.sql.Date;

import app.adapters.pet.entity.PetEntity;
import app.adapters.petOwner.entity.PetOwnerEntity;
import app.adapters.user.entity.UserEntity;
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
@Table (name = "orders")
public class OrderEntity {
	public OrderEntity(Order order, PetEntity petEntity, PetOwnerEntity petOwnerEntity, UserEntity userEntity) {
		//this.orderId = order.getOrderId();
		this.ownerId = petOwnerEntity;
		this.petId = petEntity;
		this.userId = userEntity;
		this.medicine = order.getMedicine();
		this.orderGeneration = order.getOrderGeneration();
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long orderId;
	
	@JoinColumn(name = "owner_id")
	@OneToOne
    private PetOwnerEntity ownerId;
	
	@JoinColumn(name = "pet_id")
	@OneToOne
    private PetEntity petId;
	
	@JoinColumn(name = "veterinarian_id")
	@OneToOne
    private UserEntity userId;
	
	@Column(name = "medicine")
    private String medicine;

	@Column(name = "order_generation")
    private Date orderGeneration;
	
	@Column(name = "status", nullable = false)
	private String status = "Vigente"; // Valor por defecto


	@Column(name = "cancellation_reason")
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

	public PetOwnerEntity getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(PetOwnerEntity ownerId) {
		this.ownerId = ownerId;
	}

	public PetEntity getPetId() {
		return petId;
	}

	public void setPetId(PetEntity petId) {
		this.petId = petId;
	}

	public UserEntity getUserId() {
		return userId;
	}

	public void setUserId(UserEntity userId) {
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

	public OrderEntity() {
	}
}
