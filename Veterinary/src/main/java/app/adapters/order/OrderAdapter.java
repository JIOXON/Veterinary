package app.adapters.order;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.adapters.order.entity.OrderEntity;
import app.adapters.order.repository.OrderRepository;
import app.adapters.user.entity.UserEntity;
import app.domain.models.*;
import app.ports.OrderPort;

@Service
public class OrderAdapter implements OrderPort{

	@Autowired
    private OrderRepository orderRepository;
	
	
	@Override
	public void saveOrder(Order order) {
	    OrderEntity orderEntity = new OrderEntity();
	    orderEntity.setOrderId(order.getOrderId());
	    orderEntity.setMedicine(order.getMedicine());
	    orderEntity.setOrderGeneration(order.getOrderGeneration());
	    //orderEntity.setUserId(userEntity.getUserId());
	    orderRepository.save(orderEntity);
	    order.setOrderId(orderEntity.getOrderId()); 
	}

	@Override
    public void cancelOrder(long idOrder) {
		throw new UnsupportedOperationException();
    }

	@Override
    public Order findByOrderId(long orderId) {
        OrderEntity Entity = orderRepository.findByOrderId(orderId);
        return convertToDomain(Entity);
    }


	@Override
    public List<Order> getAllOrders() {
        throw new UnsupportedOperationException();
    }

	public List<Order> findAllOrders() {
        throw new UnsupportedOperationException();
    }

	@Override
	public boolean existOrder(long orderId) {
		return orderRepository.existsByOrderId(orderId);
	}

	private Order convertToDomain(OrderEntity orderEntity) {
	    Order order = new Order();
	    order.setOrderId(orderEntity.getOrderId());
	    order.setMedicine(orderEntity.getMedicine());
	    order.setOrderGeneration(orderEntity.getOrderGeneration());
	    order.setUserId(orderEntity.getUserId().getUserId());  // Veterinarian ID
	    order.setOwnerId(orderEntity.getOwnerId().getOwnerId());
	    order.setPetId(orderEntity.getPetId().getPetId());
	    return order;
	}
	
	public Order createOrder(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderRepository.save(orderEntity);
        return adapter(orderEntity);
    }
	
	private Order adapter(OrderEntity orderEntity) {
	    Order order = new Order();
	    order.setOrderId(orderEntity.getOrderId());
	    order.setPetId(orderEntity.getPetId().getPetId());
	    order.setOwnerId(orderEntity.getOwnerId().getOwnerId());
	    order.setUserId(orderEntity.getUserId().getUserId());
	    order.setMedicine(orderEntity.getMedicine());
	    order.setOrderGeneration(orderEntity.getOrderGeneration());
	    return order;
	}

	public OrderRepository getOrderRepository() {
		return orderRepository;
	}

	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	public OrderAdapter() {
	}
}