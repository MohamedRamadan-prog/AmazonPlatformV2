package amazon.layer.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amazon.layer.domainn.User;
import amazon.layer.repository.UserRepository;

@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Set<User> getInActiveSellers() {
		Set<User> inActiveSellers = userRepository.findBySpecificRoles("ROLE_SELLER");
		return inActiveSellers;
	}

	@Override
	public void activateSeller(String sellerEmail) {

		User seller = userRepository.findByEmail(sellerEmail);

		if (seller != null) {
			seller.setActive(true);
			userRepository.save(seller);
		}

	}

}
