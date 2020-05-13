package amazon.layer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import amazon.layer.domainn.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
