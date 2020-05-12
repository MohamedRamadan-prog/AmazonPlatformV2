package amazon.layer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amazon.layer.domainn.Product;
import amazon.layer.domainn.User;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	void findBySeller(User seller);

}
