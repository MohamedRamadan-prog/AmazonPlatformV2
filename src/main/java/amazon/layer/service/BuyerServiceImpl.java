package amazon.layer.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amazon.layer.domainn.User;
import amazon.layer.repository.UserRepository;

@Service
public class BuyerServiceImpl implements BuyerService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void followSeller(String buyerEmail, String sellerEmail) {
		User buyer = userRepository.findByEmail(buyerEmail);
		User seller = userRepository.findByEmail(sellerEmail);

		buyer.getBuyerFollowing().add(seller);
		userRepository.save(buyer);

	}

	@Override
	public Set<User> getBuyerFlowingList(String buyerEmail) {

		User buyer = userRepository.findByEmail(buyerEmail);
		return buyer.getBuyerFollowing();
	}

	@Override
	public void unfollowSeller(String buyerEmail, String sellerEmail) {
		User buyer = userRepository.findByEmail(buyerEmail);
		User seller = userRepository.findByEmail(sellerEmail);

		buyer.getBuyerFollowing().remove(seller);
		userRepository.save(buyer);

	}

}
