package amazon.layer.controller;

import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import amazon.layer.domainn.Address;
import amazon.layer.domainn.Order;
import amazon.layer.domainn.OrderStatus;
import amazon.layer.domainn.Payment;
import amazon.layer.domainn.User;
import amazon.layer.service.OrderService;
import amazon.layer.service.ReportManagerService;
import amazon.layer.service.UserService;
import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("orders")
@SessionAttributes({"addedshippingAddress","addedBillingAddress","addedpayment","currentOrder","shoppingCart","points"})


public class OrderController {

	@Autowired
	OrderService orderService;
	
	@Autowired
	UserService userService;

	@Autowired
	ReportManagerService reportManagerService;

	@RequestMapping("/activeList")
	public String ordersList(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String Seller_email = authentication.getName();
		System.out.println(Seller_email);
		Set<Order> orders = orderService.getOrdersOfSeller(Seller_email);
		for (Order order : orders) {
			System.out.println(order.getTotalPrice());
			System.out.println(order.getBillingAddress().getState());
		}
		model.addAttribute("orders", orders);
		return "sellerOrders";
	}

	@RequestMapping(value = "/updateOrderStatus")
	public String updateOrderStaus(@RequestParam("status") String status, @RequestParam("id") Long id) {
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

	@RequestMapping("/CreateShippingAddress")
	public String CreateShippingAddress(@ModelAttribute("ShippingAddress") Address shippingAdrress) {
		return "OrderShippingAddress";

	}

	@RequestMapping("/SetShippingAddress")
	public String SetShippingAddress(@ModelAttribute("ShippingAddress") Address shippingAdrress, HttpSession session) {
		session.setAttribute("addedshippingAddress", shippingAdrress);
		Address shAddress = (Address) session.getAttribute("addedshippingAddress");
		System.out.println(shAddress.getCity());

		return "redirect:/orders/CreateBillingAddress";
	}

	@RequestMapping("/CreateBillingAddress")
	public String CreateBillingAddress(@ModelAttribute("BillingAddress") Address billingAddress) {
		return "OrderBillingAddress";

	}

	@RequestMapping("/setBillingAddress")
	public String setBillingAddress(@ModelAttribute("BillingAddress") Address billingAddress, HttpSession session) {

		session.setAttribute("addedBillingAddress", billingAddress);
		Address biAddress = (Address) session.getAttribute("addedBillingAddress");
		System.out.println(biAddress.getCity());

		return "redirect:/orders/confirmOrder";
	}

	@RequestMapping("/confirmOrder")
	public String confirmOrder(HttpSession session,Model model , Authentication authentication)
	{	
		String username = authentication.getName();
		User currenrUser = userService.getUserByEmail(username);
		currenrUser.setPoints(currenrUser.getPoints()+5);
		System.out.println(currenrUser.getPoints());
		userService.saveUser(currenrUser);

		session.setAttribute("points",currenrUser.getPoints());

		return "ConfirmPage";
	}

	@RequestMapping("/placeOrder")
	public String placeOrder(@ModelAttribute("p")Integer points , HttpSession session,Authentication authentication,Model model,SessionStatus status)
	{	
		System.out.println("palce order"+ points);
		System.out.println();
		
		Payment payment = (Payment) session.getAttribute("addedpayment");
		Address shipAddress = (Address) session.getAttribute("addedshippingAddress");
		Address billAddress = (Address) session.getAttribute("addedBillingAddress");
		Hashtable<Long, Integer> cart = (Hashtable<Long, Integer>) session.getAttribute("shoppingCart");
		String username = authentication.getName();

		User currenrUser = userService.getUserByEmail(username);

		orderService.placeOrder( payment, shipAddress, billAddress, cart , username, points);
		
		status.setComplete();
		
		session.setAttribute("shoppingCart", new Hashtable<Long,Integer>());

		return "redirect:/buyer/ordersHistory";
	}

	@RequestMapping(value = "/cancelOrder")
	public String cancelOrder(@RequestParam("orderId") Long id) {

		boolean isCancelled = orderService.cancelOrder(id);

		// TODO handle if order can not be cancelled
		return "redirect:/buyer/orderHistory";
	}

	@RequestMapping(value = "/generateInvoice")
	public String downloadInvoice(@RequestParam("orderId") Long id) throws FileNotFoundException, JRException {

		reportManagerService.generatePdfInvoice(id);

		return "redirect:/buyer/ordersHistory";
	}
}
