package app.ports;

import app.domain.models.Order;
import java.util.List;

public interface OrderPort {
	public void saveOrder(Order order);
	public void cancelOrder(long orderId);
	public Order findById(long orderId);
	public boolean existOrder(long orderId);
	public List<Order> getAllorders();
	public List<Order> findAllOrders();
}