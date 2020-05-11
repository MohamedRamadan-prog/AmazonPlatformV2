package amazon.layer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import amazon.layer.domainn.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("from Product as p where p.seller.email = :username")
    public Set<Product> findByUserName(@Param("username") String username);

}
