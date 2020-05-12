package amazon.layer.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import amazon.layer.domainn.User;
import amazon.layer.service.BuyerService;

@Controller
@RequestMapping({ "/buyer" })
public class BuyerController {

	@Autowired
	private BuyerService buyerService;

	@RequestMapping(value = "/success")
	public String showSellerProducts() {
		return "testFollowUnfollow.html";
	}

	@RequestMapping(value = "/followSeller", method = RequestMethod.POST)
	public String followSeller(@RequestParam("sellerEmail") String sellerEmail) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String buyerEmail = ((UserDetails) principal).getUsername();
		buyerService.followSeller(buyerEmail, sellerEmail);

		return "redirect:/buyer/success";
	}

	@RequestMapping(value = "/unfollowSeller", method = RequestMethod.POST)
	public String unfollowSeller(@RequestParam("sellerEmail") String sellerEmail) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String buyerEmail = ((UserDetails) principal).getUsername();
		buyerService.unfollowSeller(buyerEmail, sellerEmail);

		return "redirect:/buyer/success";
	}

	@RequestMapping(value = "/buyerFollowing", method = RequestMethod.GET)
	public String unfollowSeller(Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String buyerEmail = ((UserDetails) principal).getUsername();
		Set<User> sellers = buyerService.getBuyerFlowingList(buyerEmail);
		model.addAttribute("sellers", sellers);
		return "testFollowUnfollow.html";
	}
}
