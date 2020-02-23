package app.web.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import app.service.UserService;
import app.service.models.UserRegisterModel;

@Controller
public class UserController {

	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}


	@GetMapping("/home")
	public ModelAndView getHome(ModelAndView modelAndView, HttpSession session) {
		modelAndView.setViewName("home");
		modelAndView.addObject("time", session.getAttribute("time"));
		return modelAndView;
	}
	
	
	@GetMapping("/register")
	public ModelAndView getRegisterView(ModelAndView modelAndView) {
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	
	@PostMapping("/register")
	public ModelAndView registerUser(@ModelAttribute UserRegisterModel userRegisterModel, ModelAndView modelAndView) {
		if(!userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPassword())) {
			return new ModelAndView("redirect:/users/register");
		}
		this.userService.register(userRegisterModel);
		
		return new ModelAndView("redirect:/users/login");
	}

	
	@GetMapping("/index")
	public ModelAndView getIndexView(ModelAndView modelAndView) {
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	@GetMapping("/login")
	public ModelAndView getLoginPage(@RequestParam(required = false) String error, ModelAndView modelAndView, Model model) {
		if(error != null) {
			model.addAttribute("error", "Error");
		}
		
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@GetMapping("/user")
	public String getUser(Principal principal){
		System.out.println(principal.getName());
	return "user";

	}

	@GetMapping("/details")
	@PreAuthorize(value = "hasAuthority('ADMIN')")
	public String getDetails(){
	return "details";
	
	}
	
	@GetMapping("/unauthorized")
	public String getUnauthorized(){
	return "unauthorized";
	
	}
}



