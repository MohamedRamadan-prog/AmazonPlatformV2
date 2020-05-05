package amazon.layer.domainn;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	@NotEmpty()
	private String name;
	@Column
	private String discription;
	@Column(nullable = false)
	private Date creationDateTime;
	@Column
	private Integer quntityAvaliable;
	@Column
	private boolean isPurchasedBefore;
	@Column
	private double price;
	@ManyToOne(cascade = CascadeType.ALL)
	@Valid
	private User seller;
	@OneToMany(cascade = CascadeType.ALL)
	@Valid
	private Set<Review> reviews;

	public Product() {
		super();
	}

	public Product(User seller) {
		super();
		this.seller = seller;
		this.creationDateTime = new Date();
		reviews = new HashSet<Review>();
		this.isPurchasedBefore = false;
	}

	public boolean isPurchasedBefore() {
		return isPurchasedBefore;
	}

	public void setPurchasedBefore(boolean isPurchasedBefore) {
		this.isPurchasedBefore = isPurchasedBefore;
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

	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void CreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public void addReviews(Review review) {

		this.reviews.add(review);
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public Integer getQuntityAvaliable() {
		return quntityAvaliable;
	}

	public void setQuntityAvaliable(Integer quntityAvaliable) {
		this.quntityAvaliable = quntityAvaliable;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

}