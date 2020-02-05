package app.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	
	@GetMapping("/home")
	public ModelAndView getHomePage(ModelAndView modelAndView) {
		modelAndView.setViewName("homeTemplates/home");
	
		return modelAndView;
	}
	
	
}
