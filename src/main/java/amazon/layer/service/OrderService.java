package amazon.layer.service;

import java.util.List;
import java.util.Set;

import amazon.layer.domainn.Order;

public interface OrderService {

	public Set<Order> getOrdersOfSeller(String sellerEmail);

	List<Order> getOrdersOfBuyer(String buyerEmail);

	public Order getOrderById(Long id);

	public void save(Order order);

	public boolean cancelOrder(Long id);
}
