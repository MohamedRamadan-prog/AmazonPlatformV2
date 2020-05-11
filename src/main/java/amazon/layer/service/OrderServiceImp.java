package amazon.layer.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amazon.layer.domainn.Order;
import amazon.layer.repository.OrderRepository;

@Service
public class OrderServiceImp implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Override
	public Set<Order> getOrdersOfSeller(String sellerEmail) {

		Set<Order> orders = orderRepository.findOrderBySellerName(sellerEmail);
		return orders;
	}

	@Override
	public Optional<Order> getOrderById(Long orderId) {
		// TODO Auto-generated method stub
		return orderRepository.findById(orderId);
	}

}
