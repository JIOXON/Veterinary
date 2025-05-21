package app.adapters.order;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.adapters.pet.entity.PetEntity;
import app.adapters.petOwner.entity.PetOwnerEntity;
import app.adapters.petOwner.repository.PetOwnerRepository;
import app.adapters.pet.repository.PetRepository;
import app.adapters.order.entity.OrderEntity;
import app.adapters.order.repository.OrderRepository;
import app.adapters.user.entity.UserEntity;
import app.domain.models.*;
import app.ports.OrderPort;
import app.adapters.user.repository.UserRepository;

@Service
public class OrderAdapter implements OrderPort{

	@Autowired
    private OrderRepository orderRepository;
	
	@Autowired
    private PetRepository petRepository;
	
	@Autowired
    private PetOwnerRepository petOwnerRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	
	@Override
	public void saveOrder(Order order) {
		//Recupera entidades
		PetEntity petEntity = petRepository.findById(order.getPetId())
	            .orElseThrow(() -> new RuntimeException("No existe una mascota con el ID especificado."));
		PetOwnerEntity petOwnerEntity = petOwnerRepository.findByPersonDocument(order.getOwnerId())
	            .orElseThrow(() -> new RuntimeException("No existe una cedula con ese numero."));
	    UserEntity userEntity = userRepository.findById(order.getUserId())
	            .orElseThrow(() -> new RuntimeException("No existe un usuario con el ID especificado."));

	    // Crear la entidad OrderEntity
	    OrderEntity orderEntity = new OrderEntity(order, petEntity, petOwnerEntity, userEntity);

	    // Guardar la orden en la base de datos
	    orderRepository.save(orderEntity); 
	}
	public Order createOrder(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderRepository.save(orderEntity);
        return adapter(orderEntity);
    }

	@Override
	public void cancelOrder(long orderId, String reason) {
	    OrderEntity entity = orderRepository.findById(orderId)
	        .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

	    entity.setStatus("Cancelado");  // Cambiamos el estado a "Cancelado"
	    entity.setCancellationReason(reason);  // Guardamos la razón
	    orderRepository.save(entity);
	}

	@Override
    public Order findByOrderId(long orderId) {
        OrderEntity Entity = orderRepository.findByOrderId(orderId);
        return convertToDomain(Entity);
    }


	@Override
	public List<Order> getAllOrders() {
	    List<OrderEntity> orderEntities = orderRepository.findAll();
	    return orderEntities.stream()
	            .map(this::convertToDomain)
	            .collect(java.util.stream.Collectors.toList());
	}

	public List<Order> findAllOrders() {
	    return getAllOrders();
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
	    order.setUserId(orderEntity.getUserId().getUserId());  // Veterinario ID
	    order.setOwnerId(orderEntity.getOwnerId().getOwnerId());
	    order.setPetId(orderEntity.getPetId().getPetId());
	    order.setStatus(orderEntity.getStatus()); // NUEVO
	    order.setCancellationReason(orderEntity.getCancellationReason()); // NUEVO
	    return order;
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