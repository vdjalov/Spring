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
import app.service.models.LoginUserServiceModel;
import app.service.models.RegisterUserServiceModel;


@Controller
@RequestMapping(value = "/users")
public class UserController {

	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@ModelAttribute("loginUser")
	public LoginUserServiceModel LoginUserServiceModel() {
		return new LoginUserServiceModel();
	}
	
	
	@ModelAttribute("registerUser")
	public RegisterUserServiceModel register() {
		return new RegisterUserServiceModel();
	}
	

	@GetMapping("/login")
	public ModelAndView getLogin(@ModelAttribute("loginUser") LoginUserServiceModel loginUserServiceModel,
			ModelAndView modelAndView) {
		modelAndView.setViewName("userTemplates/login");
		return modelAndView;
	}
	
	
	@PostMapping("/login")
	public ModelAndView logInUser(@Valid @ModelAttribute("loginUser") LoginUserServiceModel loginUserServiceModel, 
			BindingResult bindingResult, HttpSession session) {
			
			if(bindingResult.hasErrors()) {
				return new ModelAndView("userTemplates/login");
			} 
			
				try {
					RegisterUserServiceModel registerUserServiceModel = this.userService.validateLogin(loginUserServiceModel);
					if(registerUserServiceModel.getHero() != null) {
						session.setAttribute("hero", registerUserServiceModel.getHero());
					}
					
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return new ModelAndView("userTemplates/login");
				}
			
			session.setAttribute("username", loginUserServiceModel.getUsername());
			return new ModelAndView("homeTemplates/home");
	}
	
	@GetMapping("/register")
	public ModelAndView getRegister(@ModelAttribute("registerUser") RegisterUserServiceModel registerUserServiceModel, ModelAndView modelAndView) {
		modelAndView.setViewName("userTemplates/register");
		return modelAndView;
	}
	
	
	@PostMapping("/register")
	public ModelAndView createUser(@Valid @ModelAttribute("registerUser") RegisterUserServiceModel registerUserServiceModel, 
				BindingResult bindingResult) {
			
			if(bindingResult.hasErrors()) {
				return new ModelAndView("userTemplates/register");
			}
			
			try {
				userService.save(registerUserServiceModel);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return new ModelAndView("userTemplates/register");
			}
			
			return new ModelAndView("userTemplates/login");
	}
}
