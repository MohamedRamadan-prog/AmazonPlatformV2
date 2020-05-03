package amazon.layer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import amazon.layer.domainn.User;
import amazon.layer.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService  {
	private final UserRepository repository;

	@Autowired
	public UserDetailServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {   
    	System.out.println("Find by email");
    	User curruser = repository.findByEmail(username);
    	System.out.println("found it");

        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPassword(),AuthorityUtils.createAuthorityList(curruser.getRoles().iterator().next().getName()));
    	System.out.println(user.getUsername() + " " + "  " + user.getPassword());
        System.out.println("match it");

        return user;
    } 
}