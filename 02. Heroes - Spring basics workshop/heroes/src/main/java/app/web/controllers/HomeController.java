package app.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import app.session.MySession;



@Controller
public class HomeController {

	
	@GetMapping("/")
	public ModelAndView index(ModelAndView modelAndView) {
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	@GetMapping("/home")
	public ModelAndView modelAndView(ModelAndView modelAndView, HttpSession httpSession) {
		Object hero = MySession.mySession.get(0).getAttribute("hero");
		modelAndView.addObject("username", MySession.mySession.get(0).getAttribute("username"));
		
			if(hero == null) {
				modelAndView.setViewName("home-hero-not-created");
			} else {
				modelAndView.addObject("hero", hero.toString());
				modelAndView.setViewName("home-with-created-hero");
			}
			
		return modelAndView;
	}
	
}
