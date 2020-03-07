package app.web.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.service.OrderService;
import app.service.models.OrderToCartView;


@Controller
@RequestMapping("/cart")
public class CartController {

	public static final String CART_VIEW = "orderTemplates/cart-details";
	
	private OrderService orderService;
	
	
	@Autowired
	public CartController(OrderService orderService) {
		this.orderService = orderService;
	}


	@GetMapping("/all")
	@PreAuthorize("hasAnyAuthority('USER', 'MODERATOR', 'ADMIN')")
	public ModelAndView getCartView(ModelAndView modelAndView, Principal principal) {
		String username = principal.getName();
		List<OrderToCartView> orders = this.orderService.findAllCartOrders(username);
		modelAndView.setViewName(CART_VIEW);
		double totalCost = orders.stream().mapToDouble(product -> product.getTotalPrice()).sum();
		modelAndView.addObject("orders", orders);
		modelAndView.addObject("totalCost", totalCost);
		return modelAndView;
	}
	
	
	@PostMapping("/remove/{orderId}")
	@PreAuthorize("hasAnyAuthority('USER', 'MODERATOR', 'ADMIN')")
	public ModelAndView removeOrderedItem(@PathVariable("orderId") int orderId) {
		this.orderService.removeOrder(orderId);
		return new ModelAndView("redirect:/cart/all");
	}
	
	
	@PostMapping("/checkout")
	@PreAuthorize("hasAnyAuthority('USER', 'MODERATOR', 'ADMIN')")
	public ModelAndView checkoutCart() {
		this.orderService.checkCartOut();
		return new ModelAndView("redirect:/orders/my");
	}
	

	
	
}












