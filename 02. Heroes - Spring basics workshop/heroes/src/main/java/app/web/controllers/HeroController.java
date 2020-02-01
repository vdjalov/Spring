package app.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.service.HeroService;
import app.service.models.CreateHeroServiceModel;
import app.service.models.HeroDetailsServiceModel;
import app.web.models.HeroDetailsViewModel;

@Controller
@RequestMapping(value = "/heroes")
public class HeroController {

	private HeroService heroService;
	private ModelMapper modelMapper;
	
	@Autowired
	public HeroController(HeroService heroService, ModelMapper modelMapper) {
		this.heroService = heroService;
		this.modelMapper = modelMapper;
	}


	@GetMapping("/create")
	public ModelAndView createHeroe(ModelAndView modelAndView) {
		modelAndView.setViewName("create-hero");
		return modelAndView;
		
	}
	
	
	@PostMapping("/create")
	public ModelAndView processHero(@ModelAttribute CreateHeroServiceModel createHeroServiceModel,ModelAndView modelAndView) {
		this.heroService.save(createHeroServiceModel);
		return new ModelAndView("redirect:/home");
		
	}
	
	
	@GetMapping("/details/{name}")
	public ModelAndView getHeroDetails(@PathVariable String name, ModelAndView modelAndView) {
		HeroDetailsServiceModel heroDetailsServiceModel= this.heroService.findHeroByName(name);
		HeroDetailsViewModel heroViewModel = this.modelMapper.map(heroDetailsServiceModel, HeroDetailsViewModel.class);
		modelAndView.addObject("heroSpecs", heroViewModel);
		modelAndView.setViewName("heroDetails");
		return modelAndView;
		
	}

	
	
}
