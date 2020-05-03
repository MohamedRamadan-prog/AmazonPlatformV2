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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="users")
public class User
{
    @Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Integer id;
    @Column(nullable=false)
    @NotEmpty()
    private String name;
    @Column(nullable=false, unique=true)
    @NotEmpty
    @Email(message="{errors.invalid_email}")
    private String email;
    @Column(nullable=false)
    @NotEmpty
    @Size(min=4)
    private String password;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String Address;

    @ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
    joinColumns = { @JoinColumn(name = "user_id") },
    inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private List<Role> roles;

    // Buyer Part
	@OneToMany(cascade = CascadeType.ALL)
	@Valid
	Set<@Valid Order> orders;

	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	Payment payment;

	@OneToMany(cascade = CascadeType.ALL)
	@Valid
	Set<@Valid Review> reviews;
	
	// Seller Part
	@OneToMany(cascade = CascadeType.ALL)
	Set<Product> products ;
	@Column
	boolean isActive;

    
    
    
    public User(){}
    
    public User(@NotEmpty String name, @NotEmpty @Email(message = "{errors.invalid_email}") String email,
		@NotEmpty @Size(min = 4) String password, List<Role> roles ,@NotEmpty String firstName ,@NotEmpty  String lastName ,@NotEmpty  String Address ) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.firstName = firstName;
		this.lastName = lastName;
		this.Address = Address;
		orders = new HashSet<Order>();
		reviews = new HashSet<Review>();
		isActive = false;
		products = new HashSet<Product>();

    }
    
 
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public List<Role> getRoles()
    {
        return roles;
    }
    public void setRoles(List<Role> roles)
    {
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

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
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


	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	 
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product>products) {
		this.products = products;
	}
	
	public void addProduct(Product product)
	{
		products.add(product);
	}
    
}