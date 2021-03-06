package amazon.layer.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import amazon.layer.domainn.Product;
import amazon.layer.dto.ProductForm;

public interface ProductService {

	List<Product> getAllProducts();

	public Set<Product> getSellerProducts(String sellerEmail);
	
	Product saveOrUpdate(@Valid ProductForm newProduct, String sellerEmail);

	Optional<Product> getProductById(Long productId);

	void update(ProductForm product, Long productId);

	boolean deleteById(Long productId);

}
