package app.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller()
@RequestMapping(value = "/users")
public class AuthController {

	@GetMapping("/login")
	public ModelAndView getLogin(ModelAndView modelAndView) {
		modelAndView.setViewName("validationTemplates/login");
		return modelAndView;
	}
	
	
	@GetMapping("/register")
	public ModelAndView getRegister(ModelAndView modelAndView) {
		modelAndView.setViewName("validationTemplates/register");
		return modelAndView;
	}
}
