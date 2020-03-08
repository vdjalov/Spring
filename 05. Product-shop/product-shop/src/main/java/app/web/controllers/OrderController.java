package app.web.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.service.OrderService;
import app.service.ProductService;
import app.service.models.OrderToCartView;
import app.web.models.ProductDetailsView;

@Controller
@RequestMapping("/orders")
public class OrderController {

	public static final String ORDER_PRODUCT_VIEW = "orderTemplates/order-product";
	public static final String MY_ORDERS_VIEW = "orderTemplates/my-orders";
	public static final String ORDER_DETAILS_VIEW = "orderTemplates/order-details";
	public static final String ALL_ORDERS_VIEW = "orderTemplates/all-orders";
	
	private ProductService productService;
	private OrderService orderService;
	
	@Autowired
	public OrderController(ProductService productService, OrderService orderService) {
		this.productService = productService;
		this.orderService = orderService;
	}


	@GetMapping("/product/{id}")
	@PreAuthorize("hasAnyAuthority('USER', 'MODERATOR', 'ADMIN')")
	public ModelAndView getOrderDetailsView(@PathVariable("id") int id, Principal principal, ModelAndView modelAndView) {
		ProductDetailsView productDetailsView = this.productService.getProductById(id);
		String name = principal.getName();
		modelAndView.addObject("name", name);
		modelAndView.addObject("product", productDetailsView);
		modelAndView.setViewName(ORDER_PRODUCT_VIEW);
		return modelAndView;
	}
	
	
	@PostMapping("/addToCart/{productId}")
	@PreAuthorize("hasAnyAuthority('USER', 'MODERATOR', 'ADMIN')")
	public ModelAndView addProductToCartView(@PathVariable("productId") int productId, @ModelAttribute OrderToCartView addOrderToCartView, Principal principal) {
		String username = principal.getName();
		try {
			this.orderService.addOrderToCart(productId, username, addOrderToCartView);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("redirect:/orders/addToCart/" + String.valueOf(productId));
		}
		return new ModelAndView("redirect:/cart/all");
	}
	
	
	@GetMapping("/my")
	@PreAuthorize("hasAnyAuthority('USER', 'MODERATOR', 'ADMIN')")
	public ModelAndView getMyOrdersView(ModelAndView modelAndView, Principal principal) {
		String username = principal.getName();
		List<OrderToCartView> orders = this.orderService.findCheckedOutOrders(username);
		modelAndView.setViewName(MY_ORDERS_VIEW);
		modelAndView.addObject("orders", orders);
		return modelAndView;
	}
	
	
	@GetMapping("/details/{orderId}")
	@PreAuthorize("hasAnyAuthority('USER', 'MODERATOR', 'ADMIN')")
	public ModelAndView getOrderDetailById(@PathVariable("orderId") int orderId, ModelAndView modelAndView) {
		OrderToCartView orderToCartView = null;
		try {
			orderToCartView = this.orderService.findOrderById(orderId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("redirect:/orders/my");
		}
		
		modelAndView.setViewName(ORDER_DETAILS_VIEW);
		modelAndView.addObject("order", orderToCartView);
		return modelAndView;
	}
	
	
	@GetMapping("/all")
	@PreAuthorize("hasAnyAuthority('USER', 'MODERATOR', 'ADMIN')")
	public ModelAndView getOrderDetailById(ModelAndView modelAndView) {
		List<OrderToCartView> orders = this.orderService.findAllOrders();
		modelAndView.setViewName(ALL_ORDERS_VIEW);
		modelAndView.addObject("orders", orders);
		return modelAndView;
	}
	
}







