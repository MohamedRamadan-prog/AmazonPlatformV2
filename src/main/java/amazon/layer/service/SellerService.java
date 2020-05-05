package amazon.layer.service;

import java.util.Set;

import amazon.layer.domainn.User;

public interface SellerService {

	public Set<User> getInActiveSellers();

	void activateSeller(String sellerEmail);
}
