package amazon.layer.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import amazon.layer.domainn.Order;
import amazon.layer.domainn.Payment;
import amazon.layer.repository.PaymentRepository;
import amazon.layer.service.PaymentService;

@Controller
@RequestMapping("/payments")
@SessionAttributes("addedpayment")
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@RequestMapping("/createPayment")
	public String createPayment(@ModelAttribute("payment") Payment payment)
	{
		return "paymentPage";
	}
	
	@RequestMapping("/setPayment")
	public String setPayment(@ModelAttribute("payment") Payment payment,HttpSession session)
	{
		session.setAttribute("addedpayment", payment);
		return "redirect:/orders/CreateShippingAddress";
	}
	
}
