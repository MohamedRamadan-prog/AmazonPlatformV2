package amazon.layer.service;

import java.util.Optional;
import java.util.Set;

import amazon.layer.domainn.Order;

public interface OrderService {

	
public Set<Order> getOrdersOfSeller(String sellerEmail);

public Optional<Order> getOrderById(Long orderId);
 	
}
