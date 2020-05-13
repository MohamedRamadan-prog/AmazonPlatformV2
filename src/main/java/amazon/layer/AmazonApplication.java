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
	public CommandLineRunner demo(RoleRepository roleRepository,UserRepository userReository,ProductRepository productRepository,OrderLineRepository orderLineRepository,OrderRepository orderRepository) {

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
			User seller = new User("seller" ,"seller@gmail.com", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",roles1 , "-"  ,  "-"  , address );
			User seller2 = new User("seller" ,"seller2@gmail.com", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",roles11 , "Ibrahim"  ,  "Mark"  ,new Address("IOWA", "fairField", "123654", "4st") );
			User seller3 = new User("seller" ,"seller3@gmail.com", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",roles22 , "Thomas"  ,  "John" ,new Address("IOWA", "fairField", "123654", "4st") );

			User admin = new User("admin", "admin@gmail.com",
					"$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2", roles2, "-", "-",
					new Address("IOWA", "fairField", "123654", "4st"));
			User buyer = new User("buyer", "buyer@gmail.com",
					"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", roles3, "-", "-",
					new Address("IOWA", "fairField", "123654", "4st"));

			
			
			
		    //-----------------------------------------------------------------------------------
		    
		    // add order for seller 2
		    Product product = new Product();
		    product.setName("tes1");
		    product.setPrice(120);
		    product.setSeller(seller2);
		    
		    Order order = new Order();		    
		    Address ship = new Address("IOWA", "fairField", "123654", "4st");
		    Address bill = new Address("IOWA", "fairField", "123654", "4st");
		    
		    order.setTotalPrice(120);
		    order.setShippingAddress(ship);
		    order.setBillingAddress(bill);
		    order.setSeller(seller2);

		    
		     /*
		    OrderLine orderline = new OrderLine(1,120,product,OrderStatus.PLACED);
		    Set<OrderLine> orderlines = new HashSet<OrderLine>();
		    orderlines.add(orderline);
		    
		    order.setOrderLines(orderlines);
		    orderline.setOrder(order);
		    */
		    
		    Set<Order> orders = new HashSet<Order>();
		    orders.add(order);
            orderRepository.save(order);			
		    //------------------------------------------------------------
			userReository.save(seller);
	        userReository.save(seller2);
			userReository.save(seller3);

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
