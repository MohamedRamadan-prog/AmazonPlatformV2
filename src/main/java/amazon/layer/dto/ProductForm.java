package amazon.layer.dto;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProductForm {

	private String name;

	private String discription;
	private Integer quntityAvaliable;
	
	@JsonIgnore 
	private MultipartFile  productImage;

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
