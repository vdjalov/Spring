package app.web.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import app.service.HeroService;
import app.web.models.HeroViewModel;

@Controller
public class HomeController {

	private HeroService heroService;
	
	
	@Autowired
	public HomeController(HeroService heroService) {
		this.heroService = heroService;
	}
	
	
	@GetMapping("/home")
	public ModelAndView getHome(ModelAndView modelAndView, HttpSession session) {
		modelAndView.setViewName("home");
		if(session.getAttribute("hero") != null) {
			List<HeroViewModel> allHeroes = this.heroService.getAllHeroes();
			modelAndView.addObject("allHeroes", allHeroes);
		}
		return modelAndView;
	}
	
}
