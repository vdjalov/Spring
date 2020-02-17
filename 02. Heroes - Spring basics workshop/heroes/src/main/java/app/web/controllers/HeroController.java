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
import app.service.models.ValidateCreateHeroModel;
import app.web.models.HeroFightViewModel;
import app.web.models.HeroViewModel;

@Controller
@RequestMapping("/heroes")
public class HeroController {

	@ModelAttribute("createHero")
	private ValidateCreateHeroModel validateCreateHeroModel() {
		return new ValidateCreateHeroModel();
		
	}
	
	private HeroService heroService;
	
	
	@Autowired
	public HeroController(HeroService heroService) {
		this.heroService = heroService;
	}


	@GetMapping("/create")
	public ModelAndView getCreateHero(@ModelAttribute("createHero") ValidateCreateHeroModel validateCreateHeroModel, ModelAndView modelAndView) {
		modelAndView.setViewName("heroTemplates/create-hero");
		return modelAndView;
	}
	
	
	@PostMapping("/create")
	public ModelAndView createHero(@Valid @ModelAttribute("createHero") ValidateCreateHeroModel validateCreateHeroModel, 
			BindingResult bindingResult, ModelAndView modelAndView, HttpSession session) {
		
		if(bindingResult.hasErrors()) {
			return new ModelAndView("heroTemplates/create-hero");
		}
		
		ValidateCreateHeroModel createHero = this.heroService.save(validateCreateHeroModel);
		
		session.setAttribute("hero", createHero);
		
		modelAndView.setViewName("/home");
		this.heroService.save(validateCreateHeroModel);
		return modelAndView;
	}
	
	
	@GetMapping("/details/{name}")
	public ModelAndView createHero(@PathVariable("name") String heroName, ModelAndView modelAndView) {
		
		HeroViewModel hero = this.heroService.findHeroByName(heroName);
		List<String> myslots = hero.getInventory().stream()
												  .map(item -> item.getSlot().toString())
												  .collect(Collectors.toList());
				
		modelAndView.addObject("myHero", hero);
		modelAndView.addObject("mySlots", myslots);
		modelAndView.setViewName("heroTemplates/heroDetails");
		return modelAndView;
	}
	
	
	@GetMapping("/fight/{enemyHeroName}")
	public ModelAndView getFightView(@PathVariable("enemyHeroName") String enemyHeroName, ModelAndView modelAndView) {
		modelAndView.setViewName("heroTemplates/fight");
		
		HeroFightViewModel heroFightViewModel = this.heroService.fightHeroes(enemyHeroName);
		modelAndView.addObject("fightDetails", heroFightViewModel);
		
		return modelAndView;
	}
	
}







