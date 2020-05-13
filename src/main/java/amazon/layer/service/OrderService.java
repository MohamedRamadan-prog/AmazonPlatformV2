package amazon.layer.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import amazon.layer.domainn.Address;
import amazon.layer.domainn.Order;
import amazon.layer.domainn.Payment;

public interface OrderService {


public void placeOrder(Payment payment,Address shipAddress,Address billAddress,Hashtable<Long,Integer> cart,String username );

public Set<Order> getOrdersOfSeller(String sellerEmail);

List<Order> getOrdersOfBuyer(String buyerEmail);

public Order getOrderById(Long id);

public void save(Order order);

public boolean cancelOrder(Long id);
}
