package amazon.layer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import amazon.layer.domainn.Address;
import amazon.layer.domainn.Review;
import amazon.layer.domainn.Role;
import amazon.layer.domainn.User;
import amazon.layer.repository.RoleRepository;
import amazon.layer.repository.UserRepository;

@SpringBootApplication
public class AmazonApplication {

	@Autowired
	ServletContext servletContext;
	public static void main(String[] args) {
		SpringApplication.run(AmazonApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(RoleRepository roleRepository, UserRepository userReository) {

		 createProductImagesFolder();
		return (args) -> {
			// ( admin/admin ,seller/user, buyer/user)
			List<Role> roles1 = new ArrayList<Role>();
			roles1.add(new Role("SELLER"));

			List<Role> roles1_2 = new ArrayList<Role>();
			roles1_2.add(new Role("SELLER"));

			List<Role> roles11 = new ArrayList<Role>();
			roles11.add(new Role("SELLER"));
			List<Role> roles22 = new ArrayList<Role>();
			roles22.add(new Role("SELLER"));
			List<Role> roles2 = new ArrayList<Role>();
			roles2.add(new Role("ADMIN"));

			List<Role> roles3 = new ArrayList<Role>();
			roles3.add(new Role("BUYER"));

			Address address = new Address("IOWA", "fairField", "123654", "4st");
			User seller = new User("seller", "seller@gmail.com",
					"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", roles1, "-", "-", address);
			User seller1 = new User("seller", "seller2@gmail.com",
					"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", roles11, "Ibrahim", "Mark",
					new Address("IOWA", "fairField", "123654", "4st"));
			User seller2 = new User("seller", "seller3@gmail.com",
					"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", roles22, "Thomas", "John",
					new Address("IOWA", "fairField", "123654", "4st"));

			User admin = new User("admin", "admin@gmail.com",
					"$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2", roles2, "-", "-",
					new Address("IOWA", "fairField", "123654", "4st"));
			User buyer = new User("buyer", "buyer@gmail.com",
					"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", roles3, "-", "-",
					new Address("IOWA", "fairField", "123654", "4st"));

			Set<Review> reviews = new HashSet<Review>();
			Review review1 = new Review("----------Comment 1-------------");
			Review review2 = new Review("----------Comment 2-------------");
			Review review3 = new Review("-----------Comment 3-------------");
			reviews.add(review1);
			reviews.add(review2);
			reviews.add(review3);

			Set<Review> reviews2 = new HashSet<Review>();
			Review review1_2 = new Review("----------Comment 1-------------");
			Review review2_2 = new Review("----------Comment 2-------------");
			Review review3_2 = new Review("-----------Comment 3-------------");
			reviews2.add(review1_2);
			reviews2.add(review2_2);
			reviews2.add(review3_2);

			seller.setReviews(reviews);
			seller2.setReviews(reviews2);

			userReository.save(seller);
			userReository.save(seller1);
			userReository.save(seller2);

			userReository.save(buyer);
			userReository.save(admin);
		};
	}

	private void createProductImagesFolder() {
		File file = new File (servletContext.getRealPath("/")+"/products");
		file.mkdir();
		 file = new File (servletContext.getRealPath("/")+"/users");
		file.mkdir();
		System.out.println(file.getPath());
		
	}
}
