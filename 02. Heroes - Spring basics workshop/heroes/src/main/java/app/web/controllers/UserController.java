package app.web.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.service.UserService;
import app.service.models.ValidateLoginServiceModel;
import app.service.models.ValidateUserRegisterModel;

@Controller
@RequestMapping(value = "/users")
public class UserController {
	
	private UserService userService;
	private HttpSession session;
	
	@Autowired
	public UserController(UserService userService, HttpSession session) {
		this.userService = userService;
		this.session = session;
	}


	@ModelAttribute("registerUser")
	private ValidateUserRegisterModel ValidateUserRegisterModel() {
		return new ValidateUserRegisterModel();
	}
	
	
	@ModelAttribute("loginUser")
	private ValidateLoginServiceModel validateLogin() {
		return new ValidateLoginServiceModel();
	}
	
	
	@GetMapping("/register")
	public ModelAndView getRegister(@ModelAttribute("registerUser") ValidateUserRegisterModel validateUserRegisterModel, ModelAndView modelAndView) {
		modelAndView.setViewName("userTemplates/register");
		return modelAndView;
	}
	
	
	@PostMapping("/register")
	public ModelAndView registerUser(@Valid @ModelAttribute("registerUser")ValidateUserRegisterModel validateUserRegisterModel, 
			BindingResult bindingResult) {
		
			boolean registrationIsValid = false;
			try {
				registrationIsValid = this.userService.validateRegistration(validateUserRegisterModel);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		
			if(bindingResult.hasErrors() || !(registrationIsValid)) {
				return new ModelAndView("userTemplates/register");
			}
		
			return new ModelAndView("redirect:/users/login");
	}
	
	
	@PostMapping("/login")
	public ModelAndView loginUser(@Valid @ModelAttribute("loginUser") ValidateLoginServiceModel validateLoginServiceModel,
			BindingResult bindingResult) {
		
		ValidateLoginServiceModel user = userService.save(validateLoginServiceModel);
		
		if(bindingResult.hasErrors() || user == null) {
			return new ModelAndView("userTemplates/login");
		}
		
		session.setAttribute("user", user);
		return new ModelAndView("redirect:/home");
	}
	
	
	@GetMapping("/login")
	public ModelAndView getLogin(@ModelAttribute("loginUser") ValidateLoginServiceModel validateLoginServiceModel, ModelAndView modelAndView) {
		modelAndView.setViewName("userTemplates/login");
		return modelAndView;
	}
	
	
	
	
	
	
}
