package amazon.layer.service;

import amazon.layer.domainn.Payment;

public interface PaymentService {

public Payment getPayment();
public Payment findById(Long id);
public void save (Payment payment);
}
