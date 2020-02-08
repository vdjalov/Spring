package app.web.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.error.userError.UserException;
import app.service.HeroService;
import app.service.UserService;
import app.service.models.LoginUserServiceModel;
import app.service.models.RegisterUserServiceModel;
import app.web.models.HeroViewModel;

@ControllerAdvice
@Controller
@RequestMapping(value = "/users")
public class UserController {

	private UserService userService;
	private HeroService heroService;
	
	@Autowired
	public UserController(UserService userService, HeroService heroService) {
		this.userService = userService;
		this.heroService = heroService;
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
			BindingResult bindingResult, HttpSession session) throws Exception {
			
		if(bindingResult.hasErrors()) {
			return new ModelAndView("userTemplates/login");
		} 
		
//			try {
				RegisterUserServiceModel registerUserServiceModel = this.userService.validateLogin(loginUserServiceModel);
				if(registerUserServiceModel.getHero() != null) {
					session.setAttribute("hero", registerUserServiceModel.getHero().getName());
				}
				
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//				return new ModelAndView("userTemplates/login");
//			}
		
		session.setAttribute("username", loginUserServiceModel.getUsername());
	
		return new ModelAndView("redirect:/home");
	}
	
	@GetMapping("/register")
	public ModelAndView getRegister(@ModelAttribute("registerUser") RegisterUserServiceModel registerUserServiceModel, ModelAndView modelAndView) {
		modelAndView.setViewName("userTemplates/register");
		return modelAndView;
	}
	
	
	@PostMapping("/register")
	public ModelAndView createUser(@Valid @ModelAttribute("registerUser") RegisterUserServiceModel registerUserServiceModel, 
				BindingResult bindingResult) throws UserException {
		
		if(bindingResult.hasErrors()) {
			return new ModelAndView("userTemplates/register");
		}
		
//		try {
			userService.save(registerUserServiceModel);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			return new ModelAndView("userTemplates/register");
//		}
		
		return new ModelAndView("userTemplates/login");
	}
	
	
	@GetMapping("/profile")
	public ModelAndView viewUserProfile(ModelAndView modelAndView, HttpSession session) {
		
		modelAndView.setViewName("userTemplates/profile");
		HeroViewModel heroViewModel = this.heroService.getHero(session.getAttribute("hero").toString());
		modelAndView.addObject("myHero", heroViewModel);
		
		return modelAndView;
	}
	
	
	@ExceptionHandler(value = {UserException.class})
	public ModelAndView handleDbExceptions(Exception e) {
	
		ModelAndView modelAndView = new ModelAndView("errorTemplates/user-register-error");
		modelAndView.addObject("message", e.getMessage());
		
		return modelAndView;
	}
	
	
	
}










