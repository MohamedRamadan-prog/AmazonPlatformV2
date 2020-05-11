package amazon.layer.mapper;

import java.util.Date;

import amazon.layer.domainn.Product;
import amazon.layer.dto.ProductForm;

public class ProductMapper {

	public static Product getDao(ProductForm productForm) {
		Product product = new Product();

		product.setName(productForm.getName());
		product.setDiscription(productForm.getDiscription());
		product.setQuntityAvaliable(productForm.getQuntityAvaliable());
		product.setPrice(productForm.getPrice());
		product.setCreationDateTime(new Date());

		return product;
	}
}
