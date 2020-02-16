package app.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.service.ItemService;
import app.service.models.ValidateCreateItemModel;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/items")
public class ItemController {

	
	private ItemService itemService;
	
	@Autowired
	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}


	@ModelAttribute("createItem")
	private ValidateCreateItemModel validateItem() {
		return new ValidateCreateItemModel();
	}
	
	
	@GetMapping("/create")
	public ModelAndView getCreateItem(@ModelAttribute("createItem") ValidateCreateItemModel validateCreateItemModel, ModelAndView modelAndView) {
		modelAndView.setViewName("itemTemplates/create-item");
		return modelAndView;
	}
	
	
	@PostMapping("/create")
	public ModelAndView createItem(@Valid @ModelAttribute("createItem") ValidateCreateItemModel validateCreateItemModel, 
				BindingResult bindingResult, ModelAndView modelAndView) {
		
			if(bindingResult.hasErrors()) {
				return new ModelAndView("itemTemplates/create-item");
			}
		
			itemService.saveItem(validateCreateItemModel);
			
			
		return new ModelAndView("redirect:/items/create");
	}
	
	@GetMapping("/merchant")
	public ModelAndView getMerchant(ModelAndView modelAndView) {
		modelAndView.setViewName("itemTemplates/merchant");
		
		return modelAndView;
	}
	
	
	
	
	@PostMapping("/merchant/{name}")
	public ModelAndView buyItem(@PathVariable String name, ModelAndView modelAndView) {
		
		itemService.buyItem(name);
			
		return new ModelAndView("redirect:/items/merchant");
		
	}
	
	
	
}
