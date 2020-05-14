package amazon.layer.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

public class ProductForm {

	@NotEmpty(message = "This field is required")
	private String name;

	@NotEmpty(message = "This field is required")
	private String discription;
	
	@NotNull
	@Max(value = 1000 , message = "max products avaliable is 1000")
	private Integer quntityAvaliable;
	
	@JsonIgnore 
	private MultipartFile  productImage;
	
	@NotNull
	@Max(value = 1000 , message = "max products avaliable is 1000")
	@Min(value = 1 , message =  "Must has price")
	private double price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
