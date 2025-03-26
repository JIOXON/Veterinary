package app.ports;

import app.domain.models.Order;
import app.domain.models.Person;

public interface OrderPort {

	public Order findByPersonId(Person personId);
	public void saveOrder(Order order);
	public Order findById(Order OrderId);
}
