package amazon.layer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amazon.layer.domainn.Product;
import amazon.layer.domainn.User;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//	@Query("select from Product p where p.seller.id =:sellerId")
	List<Product> findBySeller(User seller);

}
