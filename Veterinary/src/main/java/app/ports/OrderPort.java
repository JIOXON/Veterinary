package app.ports;

import app.domain.models.Order;
import java.util.List;

public interface OrderPort {
	public void saveOrder(Order order);
	public void cancelOrder(long OrderId);
	public Order findById(Order OrderId);
	public boolean existOrder(long OrderId);
	public List<Order> getAllorders();
	public List<Order> findAllOrders();
}