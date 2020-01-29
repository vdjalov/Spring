package app.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.service.implementations.UserService;
import app.service.models.UserRegisterModel;

@Controller
@RequestMapping(value = "/users")
public class RegisterController {
	
	private UserService userService;
	
	@Autowired
	public RegisterController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/register")
	public ModelAndView registerUser(ModelAndView modelAndView) {
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	@PostMapping("/register")
	public ModelAndView createUser(@ModelAttribute UserRegisterModel user, ModelAndView modelAndView) {
		if(userService.save(user)) {
			return new ModelAndView("redirect:/users/login");
		} else {
			return new ModelAndView("redirect:/users/register");
		}
		
	}
	
	
	
	
	
}
