package amazon.layer.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
	public Set<Product> getProductsByID(String username) {
		return productRepository.findByUserName(username);
	}

	@Override
	public Product saveOrUpdate(@Valid ProductForm newProduct, String sellerEmail) {

		User seller = userRepository.findByEmail(sellerEmail);

		Product product = ProductMapper.getDao(newProduct);
		product.setSeller(seller);
		return productRepository.save(product);
	}

	@Override
	public Optional<Product> getProductById(Long productId) {

		return productRepository.findById(productId);
	}

	@Override
	public void update(ProductForm product, Long productId) {

		Product oldProduct = getProductById(productId).get();

		productRepository.save(updateProduct(oldProduct, product));

	}

	private Product updateProduct(Product oldProduct, ProductForm product) {
		oldProduct.setName(product.getName());
		oldProduct.setDiscription(product.getDiscription());
		oldProduct.setPrice(product.getPrice());
		oldProduct.setQuntityAvaliable(product.getQuntityAvaliable());
		return oldProduct;
	}

	@Override
	public boolean deleteById(Long productId) {
		Product product = getProductById(productId).get();
		if (product.isPurchasedBefore())
			return false;

		productRepository.delete(product);
		return false;
	}

}
