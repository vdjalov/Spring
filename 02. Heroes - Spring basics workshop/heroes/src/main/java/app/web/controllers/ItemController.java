package app.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.service.ItemService;
import app.service.models.CreateItemServiceModel;


@Controller
@RequestMapping(value = "/items")
public class ItemController {

	private ItemService itemService;
	
	
	@Autowired
	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}


	@GetMapping("/create")
	public ModelAndView createItem(ModelAndView modelAndView, HttpSession httpSession) {
		modelAndView.setViewName("create-item");
		
		Object hero = httpSession.getAttribute("hero");
		modelAndView.addObject("hero", hero.toString());
		modelAndView.addObject("username", httpSession.getAttribute("username"));
		
		return modelAndView;
	}
	
	
	@PostMapping("/create")
	public ModelAndView saveItem(@ModelAttribute CreateItemServiceModel createItemServiceModel, ModelAndView modelAndView) {
		this.itemService.save(createItemServiceModel);
		
		return new ModelAndView("redirect:/items/create");
	}
}
