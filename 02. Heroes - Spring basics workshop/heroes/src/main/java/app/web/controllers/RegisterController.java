package app.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.data.entities.User;

@Controller
@RequestMapping(value = "/users")
public class RegisterController {
	
	
	@GetMapping("/register")
	public ModelAndView registerUser(ModelAndView modelAndView) {
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	@PostMapping("/register")
	public ModelAndView createUser(@ModelAttribute User user,ModelAndView modelAndView) {
		System.out.println();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	
}
