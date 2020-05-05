package amazon.layer;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import amazon.layer.domainn.Address;
import amazon.layer.domainn.Role;
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
			List<Role> roles11 = new ArrayList<Role>();
			roles1.add(new Role("SELLER"));
			List<Role> roles22 = new ArrayList<Role>();
			roles1.add(new Role("SELLER"));
			List<Role> roles2 = new ArrayList<Role>();
			roles2.add(new Role("ADMIN"));
			
			List<Role> roles3 = new ArrayList<Role>();
			roles3.add(new Role("BUYER"));
			
			Address address = new Address("IOWA", "fairField", "123654", "4st");
			User seller = new User("seller" ,"seller@gmail.com", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",roles1 , "Ahmed"  ,  "Ali"  , address );
			User seller1 = new User("seller" ,"seller2@gmail.com", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",roles11 , "Ibrahim"  ,  "Mark"  ,new Address("IOWA", "fairField", "123654", "4st") );
			User seller2 = new User("seller" ,"seller3@gmail.com", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",roles22 , "Thomas"  ,  "John" ,new Address("IOWA", "fairField", "123654", "4st") );

			User admin = new  User("admin","admin@gmail.com", "$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2", roles2, "-" , "-"  ,new Address("IOWA", "fairField", "123654", "4st") );
			User buyer = new  User("buyer","buyer@gmail.com", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",roles3, "-"  , "-"  ,new Address("IOWA", "fairField", "123654", "4st"));

			userReository.save(seller);
			userReository.save(seller1);
			userReository.save(seller2);

			userReository.save(buyer); 
			userReository.save(admin);
		};
	}
}
