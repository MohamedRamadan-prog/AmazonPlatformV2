package amazon.layer.domainn;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class OrderLine {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	@Column
	private int quantity;
	@Column
	private double price;
	@OneToOne(cascade = CascadeType.ALL)
	private Seller seller;
	@OneToOne(cascade = CascadeType.ALL)
	private Product product;
	@ManyToOne(cascade = CascadeType.ALL)
	private Order order;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	public OrderLine(int quantity, double price, Seller seller, Product product, OrderStatus orderStatus) {
		super();
		this.quantity = quantity;
		this.price = price;
		this.seller = seller;
		this.product = product;
		this.orderStatus = orderStatus;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void calculatePrice() {
		this.price = quantity * product.getPrice();
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

}
