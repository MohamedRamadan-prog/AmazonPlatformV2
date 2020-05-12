package amazon.layer.service;

import java.util.Hashtable;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;

import amazon.layer.domainn.Address;
import amazon.layer.domainn.Order;
import amazon.layer.domainn.Payment;

public interface OrderService {

	
public Set<Order> getOrdersOfSeller(String sellerEmail);
public Order getOrderById(Long id);	
public void save(Order order);
public void placeOrder(Payment payment,Address shipAddress,Address billAddress,Hashtable cart,String username );

}
