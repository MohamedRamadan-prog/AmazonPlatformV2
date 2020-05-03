package amazon.layer.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import amazon.layer.domainn.Role;
import amazon.layer.domainn.User;

public  class UserMapper {

	public static User UserDto(String name , String password , String email , String role )
	{
		
		User newUser = new User();
		String pwd =password;
    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
    	String hashPwd = bc.encode(pwd);
    	
    	newUser.setName(name);
    	newUser.setPassword(hashPwd);
    	newUser.setEmail(email);
    	Role newrole =  new Role();
    	newrole.setName(role);
    	List<Role> roles = new ArrayList<Role>();
		roles.add(newrole);
		newUser.setRoles(roles);
		newUser.setAddress("-");
		newUser.setFirstName("-");
		newUser.setLastName("-");

		
	return newUser;
	}
	
}
