package amazon.layer.domainn;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	@NotEmpty
	private String name;
	@Column(unique = true)
	@NotEmpty
	@Email(message = "{errors.invalid_email}")
	private String email;
	@Column
	@NotEmpty
	@Size(min = 4)
	private String password;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;

	@ManyToMany( fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<Role> roles;

	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	Payment payment;

	@OneToMany(cascade = CascadeType.ALL)
	@Valid
	Set<@Valid Review> reviews;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "buyer_following", joinColumns = @JoinColumn(name = "BUYER_ID", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "SELLER_ID", referencedColumnName = "id"))
	private Set<User> buyerFollowing;

	@Column
	boolean isActive;

	@Column
	Integer points;
	
	public User() {
	}

	public User(@NotEmpty String name, @NotEmpty @Email(message = "{errors.invalid_email}") String email,
			@NotEmpty @Size(min = 4) String password, Set<Role> roles, @NotEmpty String firstName,
			@NotEmpty String lastName, Address address) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.reviews = new HashSet<Review>();
		this.isActive = false;
		this.points = 0;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public Set<User> getBuyerFollowing() {
		return buyerFollowing;
	}

	public void setBuyerFollowing(Set<User> buyerFollowing) {
		this.buyerFollowing = buyerFollowing;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}
	
}