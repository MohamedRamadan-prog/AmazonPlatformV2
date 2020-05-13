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
import amazon.layer.domainn.Order;
import amazon.layer.domainn.OrderLine;
import amazon.layer.domainn.OrderStatus;
import amazon.layer.domainn.Product;
import amazon.layer.domainn.Review;
import amazon.layer.domainn.Role;
import amazon.layer.domainn.User;
import amazon.layer.repository.OrderLineRepository;
import amazon.layer.repository.OrderRepository;
import amazon.layer.repository.ProductRepository;
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
	public CommandLineRunner demo(RoleRepository roleRepository, UserRepository userReository,
			ProductRepository productRepository, OrderLineRepository orderLineRepository,
			OrderRepository orderRepository) {

		createProductImagesFolder();
		return (args) -> {

			roleRepository.save(new Role("ROLE_SELLER"));
			roleRepository.save(new Role("ROLE_BUYER"));
			roleRepository.save(new Role("ROLE_ADMIN"));

			Address address = new Address("IOWA", "fairField", "123654", "4st");
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_SELLER").get());
			User seller = new User("seller", "seller@gmail.com",
					"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", roles, "Ali", "Adam", address);

			HashSet<Role> roles2 = new HashSet<>();
			roles2.add(roleRepository.findByName("ROLE_SELLER").get());
			User seller2 = new User("seller", "seller2@gmail.com",
					"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", roles2, "Ibrahim", "Mark",
					new Address("IOWA", "fairField", "123654", "4st"));

			HashSet<Role> roles3 = new HashSet<>();
			roles3.add(roleRepository.findByName("ROLE_ADMIN").get());
			User admin = new User("admin", "admin@gmail.com",
					"$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2", roles3, "-", "-",
					new Address("IOWA", "fairField", "123654", "4st"));

			HashSet<Role> roles4 = new HashSet<>();
			roles4.add(roleRepository.findByName("ROLE_BUYER").get());

			User buyer = new User("buyer", "buyer@gmail.com",
					"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", roles4, "-", "-",
					new Address("IOWA", "fairField", "123654", "4st"));

			userReository.save(seller);
			userReository.save(seller2);
			userReository.save(buyer);
			userReository.save(admin);
			// -----------------------------------------------------------------------------------

		};
	}

	private void createProductImagesFolder() {
		File file = new File(servletContext.getRealPath("/") + "/products");
		file.mkdir();
		System.out.println(servletContext.getRealPath("/") + "/products");

		System.out.println(servletContext.getRealPath("/") + "/invoices");
		file = new File(servletContext.getRealPath("/") + "/invoices");
		file.mkdir();
		file = new File(servletContext.getRealPath("/") + "/users");
		file.mkdir();
		System.out.println(file.getPath());

	}
}
