package app.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/heroes")
public class HeroeController {

	
	@GetMapping("/create")
	public ModelAndView createHeroe(ModelAndView modelAndView) {
		modelAndView.setViewName("create-hero");
		return modelAndView;
		
	}
	
	
}
