package app.service.implementations;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.data.models.Order;
import app.data.models.Product;
import app.data.models.User;
import app.data.repository.OrderRepository;
import app.service.OrderService;
import app.service.ProductService;
import app.service.UserService;
import app.service.models.OrderToCartView;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;
	private ProductService productService;
	private UserService userService;
	private ModelMapper modelMapper;
	
	
	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, ProductService productService, UserService userService,
			ModelMapper modelMapper) {
		this.orderRepository = orderRepository;
		this.productService = productService;
		this.userService = userService;
		this.modelMapper = modelMapper;
	}


	@Override
	public void addOrderToCart(int productId, String username, OrderToCartView addOrderToCartView) throws Exception {
		Optional<Product> product = this.productService.findProductById(productId);
			if(product.get() == null) {
				throw new Exception("Invalid product.");
			}
		User user = (User) this.userService.loadUserByUsername(username);	
		double totalPrice = calculateTotalPrice(product.get().getPrice(), addOrderToCartView.getQuantity());
		
		Order order = this.modelMapper.map(addOrderToCartView, Order.class);
		order.setCustomer(user);
		order.setDate(new Date());
		order.setProduct(product.get());
		order.setTotalPrice(totalPrice);
		this.orderRepository.save(order);
	}


	@Override
	public List<OrderToCartView> findAllCartOrders(String username) {
		List<OrderToCartView> orders = this.orderRepository.findAll().stream()
						   .filter(order -> order.isPaid() == false && order.getCustomer().getUsername().equals(username))
						   .map(order -> this.modelMapper.map(order, OrderToCartView.class))	
						   .collect(Collectors.toList());
		return orders;
	}
	
	
	@Override
	public void removeOrder(int orderId) {
		this.orderRepository.deleteById(orderId);
	}
	

	@Override
	public List<OrderToCartView> findCheckedOutOrders(String username) {
		List<OrderToCartView> orders = this.orderRepository.findAll().stream()
						   .filter(order -> order.isPaid() == true && order.getCustomer().getUsername().equals(username))
						   .map(order -> this.modelMapper.map(order, OrderToCartView.class))
						   .collect(Collectors.toList());
		return orders;
	}
	

	@Override
	public void checkCartOut(String username) {
		this.orderRepository.findAll().stream()
							.filter(order -> order.isPaid() == false && order.getCustomer().getUsername().equals(username))
							.forEach(order -> {
								order.setPaid(true);
								this.orderRepository.saveAndFlush(order);
							});
		
	}
	
	
//	Calculate order cost 
	private double calculateTotalPrice(double price, int quantity) {
		return price * quantity;
	}


	@Override
	public OrderToCartView findOrderById(int orderId) throws Exception {
		Optional<Order> order = this.orderRepository.findById(orderId);
		if(order.get() == null) {
			throw new Exception("Order does not exist");
		}
		OrderToCartView orderToCartView = this.modelMapper.map(order.get(), OrderToCartView.class);
		
		return orderToCartView;
	}


	@Override
	public List<OrderToCartView> findAllOrders() {
		List<OrderToCartView> orders = this.orderRepository.findAll().stream()
										   .map(order -> this.modelMapper.map(order, OrderToCartView.class))
										   .collect(Collectors.toList());
		return orders;
	}
	
}

















