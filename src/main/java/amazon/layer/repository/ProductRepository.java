package amazon.layer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import amazon.layer.domainn.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
