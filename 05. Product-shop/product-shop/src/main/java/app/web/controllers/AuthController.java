package app.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.service.UserService;
import app.service.models.LoginUserServiceModel;
import app.service.models.RegisterUserServiceModel;

@Controller()
@RequestMapping(value = "/users")
public class AuthController {

	private static final String HOME_TEMPLATE = "home";
	private static final String REGISTER_TEMPLATE = "validationTemplates/register";
	private static final String LOGIN_TEMPLATE = "validationTemplates/login";
	
	private UserService userService;
	

	@Autowired
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	
	
	
	@ModelAttribute("registerUser")
	public RegisterUserServiceModel registerUserServiceModel() {
		return new RegisterUserServiceModel();
	}
	
	@ModelAttribute("loginUser") 
	public LoginUserServiceModel loginUserServiceModel() {
		return new LoginUserServiceModel();
	}
	
	@GetMapping("/login")
	@PreAuthorize("isAnonymous()")
	public ModelAndView getLogin(@ModelAttribute("loginUser") LoginUserServiceModel loginUserServiceModel, ModelAndView modelAndView) {
		modelAndView.setViewName(LOGIN_TEMPLATE);
		return modelAndView;
	}
	
	
	
	@GetMapping("/register")
	@PreAuthorize("isAnonymous()")
	public ModelAndView getRegister(@ModelAttribute("registerUser") RegisterUserServiceModel registerUserServiceModel, ModelAndView modelAndView) {
		modelAndView.setViewName(REGISTER_TEMPLATE);
		return modelAndView;
	}
	
	
	@PostMapping("/register")
	public ModelAndView validateRegister(@Valid @ModelAttribute("registerUser") RegisterUserServiceModel registerUserServiceModel,
			 BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ModelAndView(REGISTER_TEMPLATE);
		}
		
		if(userService.save(registerUserServiceModel) == null) {
			return new ModelAndView(REGISTER_TEMPLATE);
		} 
		return new ModelAndView("redirect:/users/login");
	}
	
	
	
}
