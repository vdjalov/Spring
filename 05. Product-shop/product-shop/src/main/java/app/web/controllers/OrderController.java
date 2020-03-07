package app.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.service.ProductService;
import app.web.models.ProductDetailsView;

@Controller
@RequestMapping("/orders")
public class OrderController {

	public static final String ORDER_PRODUCT_VIEW = "orderTemplates/order-product";
	
	private ProductService productService;
	
	
	@Autowired
	public OrderController(ProductService productService) {
		this.productService = productService;
	}


	@GetMapping("/product/{id}")
	public ModelAndView getOrderDetailsView(@PathVariable("id") int id, ModelAndView modelAndView) {
		ProductDetailsView productDetailsView = this.productService.getProductById(id);
		modelAndView.addObject("product", productDetailsView);
		modelAndView.setViewName(ORDER_PRODUCT_VIEW);
		return modelAndView;
	}
	
	
	
}
