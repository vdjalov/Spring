package app.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {


	@GetMapping("/index")
	public ModelAndView getIndex(ModelAndView modelAndView) {
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
}
