package amazon.layer.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import amazon.layer.domainn.OrderLine;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

}

