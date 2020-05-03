
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	@Column
	private LocalDate creationDate;
	@Column
	private double totalPrice;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<OrderLine> orderLines;

	public Order() {
		this.orderLines = new HashSet<OrderLine>();

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
}