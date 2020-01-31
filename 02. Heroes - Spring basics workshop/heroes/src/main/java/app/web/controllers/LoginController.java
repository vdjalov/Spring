package app.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.service.UserService;
import app.service.models.UserLoginModel;

@Controller
@RequestMapping(value = "/users")
public class LoginController {

	public UserService userService;
	
	@Autowired
	public LoginController(UserService userService) {
		this.userService = userService;
	}


	@GetMapping("/login")
	public ModelAndView getLoginView(ModelAndView modelAndView) {
		modelAndView.setViewName("login");
		return modelAndView;
	}
	

	
	@PostMapping("/login")
	public ModelAndView loginUser(@ModelAttribute UserLoginModel userLoginModel, HttpSession session) {
			
		UserLoginModel user = this.userService.verifyLogin(userLoginModel);
		
			if(user != null && user.getHero() != null) {
				session.setAttribute("username", user.getUsername());
				session.setAttribute("hero", user.getHero().getName());
			} else if(user!= null) {
				session.setAttribute("username", user.getUsername());
				session.setAttribute("hero", null);
			} else {
				return new ModelAndView("redirect:/users/login");
			}
		
		return new ModelAndView("redirect:/home");
	}
	
	
}
