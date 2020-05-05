package amazon.layer.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amazon.layer.domainn.Product;
import amazon.layer.domainn.User;
import amazon.layer.dto.ProductForm;
import amazon.layer.mapper.ProductMapper;
import amazon.layer.repository.ProductRepository;
import amazon.layer.repository.UserRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();

	}

	@Override
	public Product addProduct(@Valid ProductForm newProduct, String sellerEmail) {

		User seller = userRepository.findByEmail(sellerEmail);

		Product product = ProductMapper.getDao(newProduct);
		product.setSeller(seller);
		return productRepository.save(product);
	}

	@Override
	public Optional<Product> getProductById(Long productId) {
		
		return productRepository.findById(productId);
	}

}
