package app.web.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.service.ItemService;
import app.service.MerchantService;
import app.service.models.CreateItemServiceModel;
import app.session.MySession;
import app.web.models.HeroItemsViewModel;


@Controller
@RequestMapping(value = "/items")
public class ItemController {

	private ItemService itemService;
	private MerchantService merchantService;
	
	@Autowired
	public ItemController(ItemService itemService, MerchantService merchantService) {
		this.itemService = itemService;
		this.merchantService = merchantService;
	}


	@GetMapping("/create")
	public ModelAndView createItem(ModelAndView modelAndView) {
		modelAndView.setViewName("create-item");
		
		System.out.println(MySession.mySession.size());
		Object hero = MySession.mySession.get(0).getAttribute("hero");
		modelAndView.addObject("hero", hero);
		
		return modelAndView;
	}
	
	
	@PostMapping("/create")
	public ModelAndView saveItem(@ModelAttribute CreateItemServiceModel createItemServiceModel, ModelAndView modelAndView) {
		this.itemService.save(createItemServiceModel);
		
		return new ModelAndView("redirect:/items/create");
	}
	
	
	@GetMapping("/merchant")
	public ModelAndView getMerchantShop(ModelAndView modelAndView, HttpSession httpSession) {
		List<HeroItemsViewModel> allItems = merchantService.getAllMerchants();
		modelAndView.addObject("allItems", allItems);
		
		modelAndView.setViewName("merchant");
		return modelAndView;
	}
	
	
	@PostMapping("/merchant")
	public ModelAndView buyItem(@PathVariable String itemName) {
		// delete item from db and add it to the hero .... messed up as i have added it already to the hero items also update hero stats... no done
		return new ModelAndView("redirect:/create/item");
		
	}
	
}




















