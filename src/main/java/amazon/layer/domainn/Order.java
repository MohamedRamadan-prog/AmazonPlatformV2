
// Decompiled by Procyon v0.5.36
// 

package amazon.layer.domainn;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;

@Entity
@Table(name = "Order_Table")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	@Column
	private LocalDate creationDate;
	@Column
	private double totalPrice;

	@OneToMany(cascade = CascadeType.ALL)
	@Valid
	private Set<@Valid OrderLine> orderLines;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shippingAddress_id", referencedColumnName = "id")
	private Address shippingAddress;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "billingAddress_id", referencedColumnName = "id")
	private Address billingAddress;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@ManyToOne(cascade = CascadeType.ALL)
	@Valid
	User seller;
	
	
	public Order() {
		this.orderLines = new HashSet<OrderLine>();
		this.orderStatus = OrderStatus.PLACED;
	}

	@PostConstruct
	public void constructCreationDate(LocalDate date) {
		this.creationDate = LocalDate.now();
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Set<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(Set<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public void addOrderLine(OrderLine orderLine) {
		this.orderLines.add(orderLine);
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	
	
}