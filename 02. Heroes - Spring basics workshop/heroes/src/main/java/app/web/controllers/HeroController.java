package app.web.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
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

import app.service.HeroService;
import app.service.models.CreateHeroServiceModel;
import app.web.models.HeroViewModel;

@Controller
@RequestMapping("/heroes")
public class HeroController {

	private HeroService heroService;
	
	
	@Autowired
	public HeroController(HeroService heroService) {
		this.heroService = heroService;
	}


	@ModelAttribute("createHero")
	private CreateHeroServiceModel createHeroServiceModel() {
		return new CreateHeroServiceModel();
	}
	
	
	@GetMapping("/create")
	public ModelAndView getCreateHeroView(@ModelAttribute("createHero") CreateHeroServiceModel createHeroServiceModel, ModelAndView modelAndView) {
		modelAndView.setViewName("heroTemplates/create-hero");
		
		return modelAndView;
	}
	
	
	@PostMapping("/create")
	public ModelAndView createHero(@Valid @ModelAttribute("createHero") CreateHeroServiceModel createHeroServiceModel, 
			BindingResult bindingResult, HttpSession session) {
		
		if(bindingResult.hasErrors()) {
			return new ModelAndView("heroTemplates/create-hero");
		}
		
		this.heroService.save(createHeroServiceModel);
		session.setAttribute("hero", createHeroServiceModel.getName());
		return new ModelAndView("redirect:/home");
		
	}
	
	
	@GetMapping("/details/{name}")
	public ModelAndView getHeroDetails(@PathVariable String name, ModelAndView modelAndView) {
		modelAndView.setViewName("heroTemplates/heroDetails");
		HeroViewModel heroViewModel = this.heroService.getHero(name);
		modelAndView.addObject("myHero", heroViewModel);
		List<String> allSlots = heroViewModel.getInventory().stream().map(item -> item.getSlot().toString()).collect(Collectors.toList());
		modelAndView.addObject("mySlots", allSlots);
		
		return modelAndView;
	}
}





