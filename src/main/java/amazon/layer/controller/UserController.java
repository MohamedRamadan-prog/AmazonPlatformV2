package amazon.layer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.SysexMessage;
import javax.validation.Valid;

import amazon.layer.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import amazon.layer.domainn.User;
import amazon.layer.dto.UserForm;
import amazon.layer.service.UserService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = { "/", "/login" })
	public String defaultlogin(Model model) {
		return "index";
	}

	@RequestMapping(value = "/home")
	public String home(Model model, Authentication authentication, RedirectAttributes rdr) {
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		boolean isAdmin = authorities.contains(new SimpleGrantedAuthority("ADMIN"));
		boolean isSeller = authorities.contains(new SimpleGrantedAuthority("SELLER"));
		if (isAdmin)
			return "redirect:review/getReviws";
		else if(isSeller){
			String email = authentication.getName();
			rdr.addAttribute("email", email);
		    return "redirect:/products/getSellersProduct";
		}
		else
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
