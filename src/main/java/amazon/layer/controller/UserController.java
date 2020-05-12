package amazon.layer.controller;

import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.security.core.userdetails.UserDetails;

import amazon.layer.dto.UserForm;
import amazon.layer.service.UserService;

@Controller
@SessionAttributes("shoppingCart")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = { "/", "/login" })
	public String defaultlogin(Model model) {
		return "login";
	}
	
	@RequestMapping(value = "/home")
	public String home(Model model,HttpSession session) {
		
		Object pri  = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails a =  (UserDetails)pri;
		
		for (GrantedAuthority gr : a.getAuthorities()) {
			if(gr.getAuthority().equals("BUYER"))
					{
						session.setAttribute("shoppingCart", new Hashtable<Long,Integer>());
					}
		}
	
		return "home";
	}

	@RequestMapping(value = "/signup")
	public String signUp(Model model) {
		model.addAttribute("signupform", new UserForm());
		return "signup";
	}

	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	public String userSave(@Valid @ModelAttribute("signupform") UserForm userForm, BindingResult bindingResult) {

		if (!userService.save(userForm, bindingResult)) {
			return "redirect:/signup";
		}

		return "redirect:/login";
	}
}
