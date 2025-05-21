package app.ports;

import app.domain.models.Order;

import java.util.List;

public interface OrderPort {
	public void saveOrder(Order order);
	void cancelOrder(long orderId, String reason);
	public boolean existOrder(long orderId);
	public List<Order> getAllOrders();
	public List<Order> findAllOrders();
	public Order findByOrderId(long orderId);
}