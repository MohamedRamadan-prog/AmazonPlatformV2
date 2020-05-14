package amazon.layer.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import amazon.layer.domainn.Payment;
import amazon.layer.service.PaymentService;

@Controller
@RequestMapping("/payments")
@SessionAttributes("addedpayment")
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@PreAuthorize("hasRole('ROLE_BUYER')")
	@RequestMapping("/createPayment")
	public String createPayment(@ModelAttribute("payment") Payment payment)
	{
		return "paymentPage";
	}
	
	@PreAuthorize("hasRole('ROLE_BUYER')")
	@RequestMapping("/setPayment")
	public String setPayment(@Valid @ModelAttribute("payment") Payment payment, BindingResult bindingResult , HttpSession session)
	{
			if(bindingResult.hasErrors())
				{
				   return "paymentPage";
				}
		session.setAttribute("addedpayment", payment);
		return "redirect:/orders/CreateShippingAddress";
	}
	
}
