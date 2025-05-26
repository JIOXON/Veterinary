package app.adapters.rest.request;

public class OrderRequest {
	private String ProductName;
    private String dateOrder;
    private long petId;
    private long OwnerId;
    private long userId;

    // Getters y setters
    
    public String getDateOrder() { 
        return dateOrder; 
    }
    public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public void setDateOrder(String dateOrder) { 
        this.dateOrder = dateOrder; 
    }

    public long getPetId() {
		return petId;
	}
	public void setPetId(long petId) {
		this.petId = petId;
	}
	public long getOwnerId() {
		return OwnerId;
	}
	public void setOwnerId(long ownerId) {
		OwnerId = ownerId;
	}
	public long getUserId() { 
        return userId; 
    }
    public void setUserId(long userId) { 
        this.userId = userId; 
    }
}
