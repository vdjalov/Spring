package app.web.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.service.CategoryService;
import app.service.ProductService;
import app.service.models.AddProductServiceModel;
import app.service.models.EditProductServiceModel;
import app.web.models.CategoryViewModel;
import app.web.models.FindAllProductsView;
import app.web.models.ProductDetailsView;

@Controller()
@RequestMapping("/products")
public class ProductController {

	public static final String ADD_PRODUCT_VIEW = "productTemplates/add-product";
	public static final String ALL_PRODUCTS_VIEW = "productTemplates/all-products";
	public static final String DETAILS_PRODUCT_VIEW = "productTemplates/details-product";
	public static final String EDIT_PRODUCT_VIEW = "productTemplates/edit-product";
	
	private CategoryService categoryService;
	private ProductService productService;
	
	
	
	@Autowired
	public ProductController(CategoryService categoryService, ProductService productService) {
		this.categoryService = categoryService;
		this.productService = productService;
	}


	@GetMapping("/add")
	@PreAuthorize("hasAnyAuthority('MODERATOR', 'ADMIN')")
	public ModelAndView getProductAddView(ModelAndView modelAndView) {
		List<CategoryViewModel> allCategories = this.categoryService.findAllCategories();
		modelAndView.addObject("categories", allCategories);
		modelAndView.setViewName(ADD_PRODUCT_VIEW);
		return modelAndView;
	}
	
	
	@PostMapping("/add")
	@PreAuthorize("hasAnyAuthority('MODERATOR', 'ADMIN')")
	public ModelAndView createNewProduct(@ModelAttribute AddProductServiceModel addProductServiceModel) {
	try {
		this.productService.saveProduct(addProductServiceModel);
	} catch (IOException e) {
		System.out.println("Issue uploading image.");
		return new ModelAndView(ADD_PRODUCT_VIEW);
	}
		return new ModelAndView("redirect:/products/all");
	}
	
	
	@GetMapping("/all")
	@PreAuthorize("hasAnyAuthority('MODERATOR', 'ADMIN')")
	public ModelAndView getAllProductsView(ModelAndView modelAndView) {
		List<FindAllProductsView> allProducts = this.productService.getAllProducts();
		modelAndView.addObject("products", allProducts);
		modelAndView.setViewName(ALL_PRODUCTS_VIEW);
		return modelAndView;
	}
	
	
	@GetMapping("/details/{id}")
	@PreAuthorize("hasAnyAuthority('USER','MODERATOR', 'ADMIN')")
	public ModelAndView getProductDetailsView(@PathVariable("id") int id, ModelAndView modelAndView) {
		ProductDetailsView productDetailsView = this.productService.getProductById(id);
		modelAndView.addObject("product", productDetailsView);
		modelAndView.setViewName(DETAILS_PRODUCT_VIEW);
		return modelAndView;
	}
	
	
	@GetMapping("/edit/{id}")
	@PreAuthorize("hasAnyAuthority('MODERATOR', 'ADMIN')")
	public ModelAndView getEditProductView(@PathVariable("id") int id, ModelAndView modelAndView) {
		List<CategoryViewModel> allCategories = categoryService.findAllCategories();
		ProductDetailsView productDetailsView = this.productService.getProductById(id);
		modelAndView.addObject("categories", allCategories);
		modelAndView.addObject("product", productDetailsView);
		modelAndView.addObject("productCategories", productDetailsView.getCategories().stream().map(cat-> cat.getName()).collect(Collectors.toList()));
		
		modelAndView.setViewName(EDIT_PRODUCT_VIEW);
		return modelAndView;
	}
	
	
	@PostMapping("/edit/{id}")
	@PreAuthorize("hasAnyAuthority('MODERATOR', 'ADMIN')")
	public ModelAndView commitEdit(@PathVariable("id") int id, @ModelAttribute EditProductServiceModel editProductServiceModel) {
		this.productService.amendProduct(editProductServiceModel, id);
		return new ModelAndView("redirect:/products/all");
	}
	
	
	@PostMapping("/delete/{id}")
	@PreAuthorize("hasAnyAuthority('MODERATOR', 'ADMIN')")
	public ModelAndView deleteProduct(@PathVariable("id") int id) {
		this.productService.deleteProductById(id);
		return new ModelAndView("redirect:/products/all");
	}
	
}














