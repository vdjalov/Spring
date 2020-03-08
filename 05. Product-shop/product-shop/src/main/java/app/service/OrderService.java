package app.service;

import java.util.List;

import app.service.models.OrderToCartView;

public interface OrderService {

	void addOrderToCart(int productId, String username, OrderToCartView addOrderToCartView) throws Exception;

	void removeOrder(int orderId);

	List<OrderToCartView> findAllCartOrders(String username);

	List<OrderToCartView> findCheckedOutOrders(String username);

	void checkCartOut(String username);

	OrderToCartView findOrderById(int orderId) throws Exception;

	List<OrderToCartView> findAllOrders();

	
	
}
