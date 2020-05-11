package amazon.layer.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import amazon.layer.domainn.Order;
import amazon.layer.domainn.OrderStatus;
import amazon.layer.domainn.User;
import amazon.layer.repository.OrderRepository;
import amazon.layer.repository.UserRepository;

@Service
public class OrderServiceImp implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public Set<Order> getOrdersOfSeller(String sellerEmail) {
		
		Set<Order> orders = orderRepository.findOrderBySellerName(sellerEmail);
		return orders;
	}
	
	public Order getOrderById(Long id)
	{
		Optional<Order> order = orderRepository.findById(id);
		return order.get();
	}

	@Override
	public void save(Order order) {
		orderRepository.save(order);
		
	}

	
	

  	
	
}
