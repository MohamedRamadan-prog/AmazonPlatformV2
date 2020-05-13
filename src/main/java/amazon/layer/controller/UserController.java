package amazon.layer.controller;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import amazon.layer.domainn.User;
import amazon.layer.dto.UserForm;
import amazon.layer.service.UserService;
import net.sf.jasperreports.engine.JRException;


@Controller
@SessionAttributes("shoppingCart")
public class UserController {

	@Autowired
	UserService userService;
	
	
	@RequestMapping("/login")
	public String defaultlogin(Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/home")
	public String home(Model model, Authentication authentication, RedirectAttributes rdr ,HttpSession session) {
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		boolean isAdmin = authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
		boolean isSeller = authorities.contains(new SimpleGrantedAuthority("ROLE_SELLER"));
		if (isAdmin)
			return "redirect:review/getReviws";
		else if(isSeller){
			String email = authentication.getName();
			rdr.addAttribute("email", email);
		    return "redirect:/products/getSellersProduct";
		    
		}
		else
		{
			session.setAttribute("shoppingCart", new Hashtable<Long,Integer>());
			return "redirect:/products/list";
		}
	}

	@RequestMapping(value = "/signup")
	public String signUp(Model model) throws FileNotFoundException, JRException {
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
	
	@RequestMapping("/userById")
	public User getUserById(@RequestParam("id") Long id)
	{
		return userService.getUserById(id).get();
		
	}
	
}
