package amazon.layer.controller;

import java.util.List;
import java.util.Set;

import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import amazon.layer.domainn.Order;
import amazon.layer.domainn.Product;
import amazon.layer.domainn.User;
import amazon.layer.service.BuyerService;
import amazon.layer.service.OrderService;
import amazon.layer.service.UserService;

@Controller
@RequestMapping({ "/buyer" })
public class BuyerController {

	@Autowired
	private BuyerService buyerService;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/success")
	public String showSellerProducts() {
		return "testFollowUnfollow.html";
	}

	@RequestMapping(value = "/followSeller", method = RequestMethod.POST)
	public String followSeller(@RequestParam("sellerEmail") String sellerEmail, Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String buyerEmail = ((UserDetails) principal).getUsername();
		buyerService.followSeller(buyerEmail, sellerEmail);
		model.addAttribute("followed", buyerService.isFollowed(buyerEmail, sellerEmail));
		return "forward:/products/product";
	}

	@RequestMapping(value = "/unfollowSeller", method = RequestMethod.POST)
	public String unfollowSeller(@RequestParam("sellerEmail") String sellerEmail, Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String buyerEmail = ((UserDetails) principal).getUsername();
		buyerService.unfollowSeller(buyerEmail, sellerEmail);
		model.addAttribute("followed", buyerService.isFollowed(buyerEmail, sellerEmail));
		return "forward:/products/product";
	}

	@RequestMapping(value = "/buyerFollowing", method = RequestMethod.GET)
	public String unfollowSeller(Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String buyerEmail = ((UserDetails) principal).getUsername();
		Set<User> sellers = buyerService.getBuyerFlowingList(buyerEmail);
		model.addAttribute("sellers", sellers);
		return "testFollowUnfollow.html";
	}

	@RequestMapping(value = "/ordersHistory", method = RequestMethod.GET)
	public String getMyOrdersHistory(Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String buyerEmail = ((UserDetails) principal).getUsername();
		List<Order> orders = orderService.getOrdersOfBuyer(buyerEmail);
		model.addAttribute("orders", orders);
		return "buyerOrderHistory";
	}
	
	@RequestMapping("/getProductSellerForBuyer")
	public String getProductSellerForBuyer(@ModelAttribute("status") Long sellerId,Model model,Authentication authentication)
	{	
		User seller = userService.getUserById(sellerId).get();
		System.out.println(seller.getId());
		
		Set<Product> products = buyerService.getProductSellerForBuyer(seller);
		String username = authentication.getName();
		model.addAttribute("followingSellers",buyerService.getBuyerFlowingList(username));
		model.addAttribute("products", products);
		return "buyerHome";
	}
	
	
}
