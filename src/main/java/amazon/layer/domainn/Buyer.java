package amazon.layer.domainn;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Buyer extends User {
	@OneToMany(cascade = CascadeType.ALL)
	@Valid
	Set<@Valid Order> orders;

	@Valid
	Payment payment;

	@OneToMany(cascade = CascadeType.ALL)
	@Valid
	Set<@Valid Review> reviews;

	public Buyer(@NotEmpty String name, @NotEmpty @Email(message = "{errors.invalid_email}") String email,
			@NotEmpty @Size(min = 4) String password, List<Role> roles, @NotEmpty String firstName,
			@NotEmpty String lastName, @NotEmpty String Address) {
		super(name, email, password, roles, firstName, lastName, Address);
		orders = new HashSet<Order>();
		reviews = new HashSet<Review>();
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public void addOrder(Order order) {
		this.orders.add(order);
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

}
