package amazon.layer.service;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amazon.layer.domainn.Address;
import amazon.layer.domainn.Order;
import amazon.layer.domainn.OrderLine;
import amazon.layer.domainn.OrderStatus;
import amazon.layer.domainn.Payment;
import amazon.layer.domainn.Product;
import amazon.layer.domainn.User;
import amazon.layer.repository.OrderRepository;
import amazon.layer.repository.UserRepository;

@Service
public class OrderServiceImp implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	UserRepository userRepository;

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
	
	public void placeOrder(Payment payment,Address shipAddress,Address billAddress,Hashtable cart,String username )
	{	
		Set<Product> products = cart.keySet();
		Hashtable<Long , List<OrderLine>> listOfSeller = new Hashtable<>();
		
		for(Product product :products)
		{
			int quantity = (int)cart.get(product);
			OrderLine orderline = new OrderLine(quantity,(product.getPrice()*quantity),product);
			
			if(listOfSeller.contains(product.getSeller().getId()))
			{
				     List<OrderLine> orderlines = listOfSeller.get(product.getSeller().getId());
				     orderlines.add(orderline);
				     listOfSeller.put(product.getSeller().getId(),orderlines);
			}else
				{
					List<OrderLine> orderlines = new ArrayList<OrderLine>();
					orderlines.add(orderline);
					listOfSeller.put(product.getSeller().getId(),orderlines);
				}
		
		}
		
		
		Set<Long> sellerId = listOfSeller.keySet();
		for(Long id : sellerId)
		{
			Order order = new Order();
			long orderPerPrice = 0 ;
			
			List<OrderLine> orderlines = listOfSeller.get(id);
			for(OrderLine orderline : orderlines)
				{
						order.addOrderLine(orderline);
						orderPerPrice += orderline.getPrice();
				}
			order.setTotalPrice(orderPerPrice);
			order.setBillingAddress(billAddress);
			order.setShippingAddress(shipAddress);
			
			User seller = userRepository.findById(id).get();
			User buyer  = userRepository.findByEmail(username);
			
			order.setSeller(seller);
			order.setBuyer(buyer);
	
			orderRepository.save(order);
		}
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
