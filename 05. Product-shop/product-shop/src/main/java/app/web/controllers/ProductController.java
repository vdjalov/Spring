package app.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.service.CategoryService;
import app.web.models.CategoryViewModel;

@Controller()
@RequestMapping("/products")
public class ProductController {

	public static final String ADD_PRODUCT_VIEW = "productTemplates/add-product";
	
	private CategoryService categoryService;
	
	@Autowired
	public ProductController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}


	@GetMapping("/add")
	public ModelAndView getProductAddView(ModelAndView modelAndView) {
		List<CategoryViewModel> allCategories = this.categoryService.findAllCategories();
		modelAndView.addObject("categories", allCategories);
		modelAndView.setViewName(ADD_PRODUCT_VIEW);
		return modelAndView;
	}
	
}
