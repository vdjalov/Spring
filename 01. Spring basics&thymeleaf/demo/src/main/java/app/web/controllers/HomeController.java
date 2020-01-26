package app.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(ModelAndView modelAndView, Model model) {
		model.addAttribute("name", "Vlad");
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
	
	@GetMapping(value = "/all")
	public ModelAndView all(ModelAndView modelAndView) {
		modelAndView.setViewName("all");
		return modelAndView;
	}
	
}
