package amazon.layer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import amazon.layer.domainn.Order;
import amazon.layer.domainn.Product;
import amazon.layer.service.OrderService;

@Controller
@RequestMapping("orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@RequestMapping("/activeList")
	public String ordersList(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String Seller_email = authentication.getName();
		System.out.println(Seller_email);
		Set<Order> orders1 = orderService.getOrdersOfSeller(Seller_email);
		for (Order order : orders1) {
			System.out.println(order.getTotalPrice());
			System.out.println(order.getBillingAddress().getState());
		}
		List<Order> orders = new ArrayList<Order>(orders1);
		for (Order order : orders) {
			// System.out.println(order.get);
			System.out.println(order.getTotalPrice());
			System.out.println(order.getBillingAddress().getState());
		}
		model.addAttribute("orders", orders);
		return "sellerOrders";
	}

	@RequestMapping("/order")
	public String getOrderDetails(@RequestParam(value = "id", required = true) Long orderId, Model model) {
		Optional<Order> order = orderService.getOrderById(orderId);
		model.addAttribute("order", order.get());
		
		return "orderDetails";
	}

}
