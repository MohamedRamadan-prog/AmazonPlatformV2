package amazon.layer;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import amazon.layer.domainn.Buyer;
import amazon.layer.domainn.Role;
import amazon.layer.domainn.Seller;
import amazon.layer.domainn.User;
import amazon.layer.repository.RoleRepository;
import amazon.layer.repository.UserRepository;


@SpringBootApplication
public class AmazonApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AmazonApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(RoleRepository roleRepository,UserRepository userReository) {
		return (args) -> {
			//( admin/admin ,seller/user, buyer/user)
			List<Role> roles1 = new ArrayList<Role>();
			roles1.add(new Role("SELLER"));
			
			List<Role> roles2 = new ArrayList<Role>();
			roles2.add(new Role("ADMIN"));
			
			List<Role> roles3 = new ArrayList<Role>();
			roles3.add(new Role("BUYER"));
			
			
			User seller = new User("seller" ,"seller@gmail.com", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",roles1 , "-"  ,  "-"  , "-"  );
			User admin = new  User("admin","admin@gmail.com", "$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2", roles2, "-" , "-"  ,"-" );
			User buyer = new  User("buyer","buyer@gmail.com", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",roles3, "-"  , "-"  ,"-" );

			userReository.save(seller);
			userReository.save(buyer); 
			userReository.save(admin);
		};
	}
}
