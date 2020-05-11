package amazon.layer.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import amazon.layer.domainn.Order;
import amazon.layer.domainn.OrderStatus;
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
		Set<Order> orders = orderService.getOrdersOfSeller(Seller_email);
		for(Order order : orders)
		{
			System.out.println(order.getTotalPrice());
			System.out.println(order.getBillingAddress().getState());
		}
		model.addAttribute("orders", orders);
		return "sellerOrders";
	}	
	
	@RequestMapping(value = "/updateOrderStatus")
	public String updateOrderStaus(@RequestParam("status")String status , @RequestParam("id") Long id)
	{
		System.out.println(id);
		System.out.println(status);
		Order order = orderService.getOrderById(id);
		order.setOrderStatus(OrderStatus.valueOf(status));
		orderService.save(order);
		return "redirect:/orders/activeList";
	}

	@RequestMapping("/order")
	public String getOrderDetails(@RequestParam(value = "id", required = true) Long orderId, Model model) {
		Order order = orderService.getOrderById(orderId);
		model.addAttribute("order", order);
		return "orderDetails";
	}
}
