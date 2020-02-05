package app.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.service.ItemService;
import app.service.models.RegisterItemServiceModel;

@Controller
@RequestMapping(value = "/items")
public class ItemController {

	private ItemService itemService;

	
	@Autowired
	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	@ModelAttribute("registerItem")
	private RegisterItemServiceModel registerItemServiceModel() {
		return new RegisterItemServiceModel();
	}
	
	
	@GetMapping("/create")
	public ModelAndView getCreateItemView(@ModelAttribute("registerItem") RegisterItemServiceModel registerItemServiceModel, ModelAndView modelAndView) {
		modelAndView.setViewName("itemTemplates/create-item");
		return modelAndView;
	}
	
	
	@PostMapping("/create")
	public ModelAndView createItem(@Valid @ModelAttribute("registerItem") RegisterItemServiceModel registerItemServiceModel, 
				BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return new ModelAndView("/itemTemplates/create-item");
		}
		
		this.itemService.save(registerItemServiceModel);
		
		return new ModelAndView("itemTemplates/merchant");
	}
	
	
}
