package amazon.layer.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amazon.layer.domainn.Order;
import amazon.layer.domainn.OrderStatus;
import amazon.layer.domainn.User;
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

	public Order getOrderById(Long id) {
		Optional<Order> order = orderRepository.findById(id);
		return order.get();
	}

	@Override
	public void save(Order order) {
		orderRepository.save(order);

	}

	@Override
	public List<Order> getOrdersOfBuyer(String buyerEmail) {

		return orderRepository.findOrderByBuyerName(buyerEmail);
	}

	@Override
	public boolean cancelOrder(Long id) {

		Order order = orderRepository.findById(id).get();

		if (order.getOrderStatus() != OrderStatus.PLACED)
			return false;

		order.setOrderStatus(OrderStatus.CANCELLED);

		orderRepository.save(order);

		return true;
	}
}
