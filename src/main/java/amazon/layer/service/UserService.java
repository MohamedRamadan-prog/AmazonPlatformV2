package amazon.layer.service;

import java.util.Optional;

import org.springframework.validation.BindingResult;

import amazon.layer.domainn.User;
import amazon.layer.dto.UserForm;

public interface UserService {

	
	public boolean save(UserForm userForm, BindingResult bindingResult);
	public Optional<User> getUserById(Long id);
	public User getUserByEmail(String username);
	public void saveUser(User user);

	
}
