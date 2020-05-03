package amazon.layer.service;

import amazon.layer.domainn.Order;

public interface ShoppingCartService {

	public void saveOrUpdate(int productId, int quantity);

	public void remove(int productId);

	public Order constructOrder();
}
