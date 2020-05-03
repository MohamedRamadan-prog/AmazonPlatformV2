package amazon.layer.domainn;

import java.util.List;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Buyer extends User {

//	List<Order> orders ;
//	Payment payment;
//	List<Review> reviews;
	
	public Buyer(@NotEmpty String name, @NotEmpty @Email(message = "{errors.invalid_email}") String email,
			@NotEmpty @Size(min = 4) String password, List<Role> roles ,@NotEmpty String firstName ,@NotEmpty  String lastName ,@NotEmpty  String Address) {
		super(name , email,password,roles , firstName,lastName,Address);
		//orders = new ArrayList<Product>();
		//reviews = new ArrayList<Product>();
	}
	
	
}
