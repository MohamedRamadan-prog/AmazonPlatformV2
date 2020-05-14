package amazon.layer.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import amazon.layer.domainn.Role;
import amazon.layer.domainn.User;
import amazon.layer.dto.UserForm;
import amazon.layer.mapper.UserMapper;
import amazon.layer.repository.RoleRepository;
import amazon.layer.repository.UserRepository;

@Service
public class UserServiceImp implements UserService{

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;

	public boolean save(UserForm userForm, BindingResult bindingResult)
	{
		if (!bindingResult.hasErrors()) { // validation errors		
    		if (userForm.getPassword().equals(userForm.getPasswordCheck())) { // check password match		
    				
    			User user = UserMapper.UserDto(userForm.getName(), userForm.getPassword(), userForm.getUsername());
    		
    			Set<Role> roles = new HashSet<Role>();
    			roles.add(roleRepository.findByName(userForm.getRole()).get());
		    	if (userRepository.findByEmail(userForm.getUsername()) == null) {
		    		user.setRoles(roles);
		    		userRepository.save(user);
		    	}
		    	else {
	    			return false;		    		
		    	}
    		}
    		else {
    			return false;
    		}
    	}
    	else {
    		return false;
    	}
    	return true;    	
    }
	
	public Optional<User> getUserById(Long id)
	{
		return userRepository.findById(id);
	}
	
	public User getUserByEmail(String username)
	{
		return userRepository.findByEmail(username);
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
}
