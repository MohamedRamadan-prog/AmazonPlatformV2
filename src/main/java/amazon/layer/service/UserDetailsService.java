package amazon.layer.service;

import org.springframework.security.core.userdetails.UserDetails;

import amazon.layer.domainn.User;



public interface UserDetailsService {

public UserDetails loadUserByUsername(String userName);

public void save(User user);

}
