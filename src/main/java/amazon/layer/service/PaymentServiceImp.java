package amazon.layer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import amazon.layer.domainn.Payment;
import amazon.layer.repository.PaymentRepository;

@Service
public class PaymentServiceImp implements PaymentService {

	@Autowired
	PaymentRepository paymentRepository;
	@Override
	public Payment getPayment() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Payment findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void save(Payment payment) {
		paymentRepository.save(payment);
	}

}
