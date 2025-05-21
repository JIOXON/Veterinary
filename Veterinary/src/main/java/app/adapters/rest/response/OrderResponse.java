package app.adapters.rest.response;

public class OrderResponse {
    private long orderId;
    private String description;
    private String status;

    public OrderResponse(long orderId, String description, String status) {
        this.orderId = orderId;
        this.description = description;
        this.status = status;
    }
    public long getOrderId() { return orderId; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    
    
}
