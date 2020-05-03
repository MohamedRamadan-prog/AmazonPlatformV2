package amazon.layer.domainn;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Seller extends User {
	
	//List<Product> products ;
	boolean isActive;
	
	public Seller(@NotEmpty String name, @NotEmpty @Email(message = "{errors.invalid_email}") String email,
			@NotEmpty @Size(min = 4) String password, List<Role> roles , String firstName ,  String lastName ,  String Address) {
		super(name , email,password,roles ,firstName , lastName , Address);
		isActive = false;
		//products = new ArrayList<Product>();
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	/*
	 
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product>products) {
		this.products = products;
	}
	
	public void addProduct(Product product)
	{
		products.add(product);
	}
	
	*/
}
