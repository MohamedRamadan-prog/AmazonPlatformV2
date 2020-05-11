package amazon.layer.service;

import java.util.Set;

import amazon.layer.domainn.User;

public interface BuyerService {

	void followSeller(String buyerEmail, String sellerEmail);

	Set<User> getBuyerFlowingList(String buyerEmail);

	void unfollowSeller(String buyerEmail, String sellerEmail);
}
