package app.adapters.rest.request;

public class CancelOrderRequest {
	private long orderId;
    private long petId;
    private String reason;

    public long getIdOrder() { return orderId; }
    public void setIdOrder(long orderId) { this.orderId = orderId; }
    public long getIdPet() { return petId; }
    public void setIdPet(long idPet) { this.petId = idPet; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}