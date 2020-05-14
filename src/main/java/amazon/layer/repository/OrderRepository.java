package amazon.layer.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import amazon.layer.domainn.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("from Order o where o.seller.email =:sellerEmail ")
	public Set<Order> findOrderBySellerName(@Param("sellerEmail") String sellerEmail);

	@Query("from Order o where o.buyer.email =:buyerEmail")
	public List<Order> findOrderByBuyerName(@Param("buyerEmail") String buyerEmail);
}
