
// Decompiled by Procyon v0.5.36
// 

package amazon.layer.domainn;

import java.util.Date;
import java.util.Set;

import javax.annotation.PostConstruct;

public class Order {
	private Date creationDate;
	private double totalPrice;
	private Set<OrderLine> orderLines;

	@PostConstruct
	public void constructCreationDate(Date date) {
		this.creationDate = new Date();
	}

	public Date getCreationDate() {
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

}