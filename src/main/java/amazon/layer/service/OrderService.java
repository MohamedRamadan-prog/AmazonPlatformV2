package amazon.layer.service;

import java.util.Set;
import amazon.layer.domainn.Order;

public interface OrderService {

	
public Set<Order> getOrdersOfSeller(String sellerEmail);
public Order getOrderById(Long id);	
public void save(Order order);
}
