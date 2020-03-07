package app.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import app.service.CategoryService;
import app.service.ProductService;

@Controller
public class HomeController {

	private CategoryService categoryService;
	
	
	@Autowired
	public HomeController(CategoryService categoryService, ProductService productService) {
		this.categoryService = categoryService;
	}


	@GetMapping("/home")
	public ModelAndView getHome(ModelAndView modelAndView) {
		modelAndView.addObject("categories", this.categoryService.findAllCategories());
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
	
}
