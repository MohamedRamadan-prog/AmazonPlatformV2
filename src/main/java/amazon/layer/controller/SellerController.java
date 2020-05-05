package amazon.layer.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import amazon.layer.domainn.User;
import amazon.layer.service.SellerService;

@Controller
@RequestMapping({ "/seller" })
public class SellerController {

	@Autowired
	private SellerService sellerService;

	@RequestMapping(value = "/inActive_Sellers", method = RequestMethod.GET)
	public String retrieveInActiveSeller(Model model) {
		Set<User> inActiveSellers = sellerService.getInActiveSellers();
		model.addAttribute("inActiveSellers", inActiveSellers);

		return "inActiveSellers";
	}

	@RequestMapping(value = "/activateSeller", method = RequestMethod.POST)
	public String activeateSeller(@RequestParam("sellerEmail") String sellerEmail,
			RedirectAttributes redirectAttributes) {

		sellerService.activateSeller(sellerEmail);
		redirectAttributes.addFlashAttribute("message", "Success");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/seller/inActive_Sellers";
	}

}
