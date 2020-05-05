package amazon.layer.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import amazon.layer.domainn.Product;
import amazon.layer.dto.ProductForm;

public interface ProductService {

	List<Product> getAllProducts();

	Product addProduct(@Valid ProductForm newProduct, String sellerEmail);

	Optional<Product> getProductById(Long productId);

}
