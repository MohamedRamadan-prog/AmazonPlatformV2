package amazon.layer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import amazon.layer.domainn.Role;
import amazon.layer.domainn.User;
import amazon.layer.dto.UserForm;
import amazon.layer.mapper.UserMapper;
import amazon.layer.repository.UserRepository;

@Service
public class UserService {

	
	@Autowired
	UserRepository userRepository;
	
	public boolean save(UserForm userForm, BindingResult bindingResult)
	{
		if (!bindingResult.hasErrors()) { // validation errors		
    		if (userForm.getPassword().equals(userForm.getPasswordCheck())) { // check password match		
    				
    			User user = UserMapper.UserDto(userForm.getName(), userForm.getPassword(), userForm.getUsername(), userForm.getRole());
		    	if (userRepository.findByEmail(userForm.getUsername()) == null) {
		    		userRepository.save(user);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "error.userexists", "Username already exists");    	
	    			return false;		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "error.pwdmatch", "Passwords does not match");    	
    			return false;
    		}
    	}
    	else {
    		return false;
    	}
    	return true;    	
    }    
}
