package app.web.controllers;

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
import app.service.models.GetCategoryServiceModel;
import app.web.models.CategoryViewModel;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	private static final String ADD_CATEGORY_TEMPLATE = "categoryTemplates/add-category";
	private static final String ALL_CATEGORIES_TEMPLATE = "categoryTemplates/all-categories";
	private static final String EDIT_CATEGORY_TEMPLATE = "categoryTemplates/edit-category";
	private static final String DELETE_CATEGORY_TEMPLATE = "categoryTemplates/delete-category";
	
	private CategoryService categoryService;
	
	
	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}


	@GetMapping("/add")
	public ModelAndView getAddCategoryView(ModelAndView modelAndView) {
		modelAndView.setViewName(ADD_CATEGORY_TEMPLATE);
		return modelAndView;
	}
	
	
	@PostMapping("/add")
	public ModelAndView addCategory(@ModelAttribute GetCategoryServiceModel getCategoryServiceModel) {
		this.categoryService.addCategory(getCategoryServiceModel);
		return new ModelAndView("redirect:/categories/all");
	}
	
	
	@GetMapping("/all")
	@PreAuthorize(value = "hasAuthority('MODERATOR')")
	public ModelAndView allCategoriesView(ModelAndView modelAndView) {
		modelAndView.setViewName(ALL_CATEGORIES_TEMPLATE);
		return modelAndView;
	}
	
	
	@GetMapping("/edit/{id}")
	@PreAuthorize(value = "hasAuthority('MODERATOR')")
	public ModelAndView getEditView(@PathVariable("id") String id, ModelAndView modelAndView) {
		CategoryViewModel categoryViewModel = null;
		try {
			categoryViewModel = this.categoryService.findById(id);
		} catch (Exception e) {
			System.out.println(e.getMessage()); // Catch exception return the right view 
			return new ModelAndView(EDIT_CATEGORY_TEMPLATE);
		
		}
		modelAndView.addObject("model", categoryViewModel);
		modelAndView.setViewName(EDIT_CATEGORY_TEMPLATE);
		return modelAndView;
	}
	
	
	
	@PostMapping("/edit/{id}")
	public ModelAndView processEdit(@PathVariable("id") int id, @ModelAttribute("name") String categoryName) {
		this.categoryService.editCategory(id, categoryName);
		return new ModelAndView("redirect:/categories/all");
	}
	
	
	@GetMapping("/delete/{id}")
	@PreAuthorize(value = "hasAuthority('MODERATOR')")
	public ModelAndView getDeleteView(@PathVariable("id") String id, ModelAndView modelAndView) {
		CategoryViewModel categoryViewModel = null;
		try {
			categoryViewModel = this.categoryService.findById(id);
		} catch (Exception e) {
			System.out.println(e.getMessage()); // Catch exception return te right view 
			return new ModelAndView(DELETE_CATEGORY_TEMPLATE);
		}
		
		modelAndView.addObject("model", categoryViewModel);
		modelAndView.setViewName(DELETE_CATEGORY_TEMPLATE);
		return modelAndView;
	}
	
	
	@PostMapping("/delete/{id}")
	public ModelAndView getDeleteView(@PathVariable("id") int id, ModelAndView modelAndView) {
		this.categoryService.deleteCategory(id);
		return new ModelAndView("redirect:/categories/all");
	}
}

















