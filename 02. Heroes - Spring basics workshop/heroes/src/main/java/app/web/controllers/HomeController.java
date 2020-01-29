package app.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@GetMapping("/")
	public ModelAndView index(ModelAndView modelAndView) {
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	@GetMapping("/home")
	public ModelAndView modelAndView(ModelAndView modelAndView, HttpSession httpSession) {
		Object hero = httpSession.getAttribute("hero");
		modelAndView.addObject("username", httpSession.getAttribute("username"));
			if(hero == null) {
				modelAndView.setViewName("home-hero-not-created");
			} else {
				modelAndView.setViewName("home-with-created-hero");
			}
			
		return modelAndView;
	}
	
}
