package amazon.layer.mapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import amazon.layer.domainn.Address;
import amazon.layer.domainn.User;

public class UserMapper {

	public static User UserDto(String firstName , String lastName, String password, String email) {

		User newUser = new User();
		String pwd = password;
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		String hashPwd = bc.encode(pwd);

		newUser.setName(firstName +" " + lastName);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setPassword(hashPwd);
		newUser.setEmail(email);
		newUser.setAddress(new Address("IOWA", "fairField", "123654", "4st"));

		return newUser;
	}

}
