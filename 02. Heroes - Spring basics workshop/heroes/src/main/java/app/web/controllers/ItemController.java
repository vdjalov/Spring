package app.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.service.ItemService;
import app.service.models.RegisterItemServiceModel;
import app.web.models.ItemViewModel;

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
		return new ModelAndView("redirect:/items/merchant");
	}
	
	
	@GetMapping("/merchant")
	public ModelAndView getMerchant(ModelAndView modelAndView) {
		List<ItemViewModel> allItems = this.itemService.getAllItems();
		modelAndView.addObject("items", allItems);
		modelAndView.setViewName("itemTemplates/merchant");
		return modelAndView;
		
	}
	
	
	
	@PostMapping("/merchant/{itemName}")
	public ModelAndView getMerchant(@PathVariable String itemName) {
		try {
			itemService.findItemByNameAndAddToHeroInventory(itemName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return new ModelAndView("redirect:/items/merchant");
	}
}















