package app.web.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.service.UserService;
import app.service.models.ValidateCreateHeroModel;
import app.service.models.ValidateLoginServiceModel;
import app.service.models.ValidateUserRegisterModel;
import app.web.models.UserViewModel;


@Controller
@RequestMapping(value = "/users")
public class UserController {
	
	private UserService userService;
	private HttpSession session;
	private ModelMapper modelMapper;
	
	@Autowired
	public UserController(UserService userService, HttpSession session, ModelMapper modelMapper) {
		this.userService = userService;
		this.session = session;
		this.modelMapper = modelMapper;
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
			BindingResult bindingResult) throws Exception {
		
			if(bindingResult.hasErrors()) {
				return new ModelAndView("userTemplates/register");
			}
			
			boolean registrationIsValid = false;
			registrationIsValid = this.userService.validateRegistration(validateUserRegisterModel);
			
			if(!registrationIsValid) {
				return new ModelAndView("userTemplates/register");
			}
			
			return new ModelAndView("redirect:/users/login");
	}
	
	
	@PostMapping("/login")
	public ModelAndView loginUser(@Valid @ModelAttribute("loginUser") ValidateLoginServiceModel validateLoginServiceModel,
			BindingResult bindingResult) {
		
		ValidateLoginServiceModel user = userService.save(validateLoginServiceModel);
		
		if(bindingResult.hasErrors() || user == null) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("userTemplates/login");
			if(user == null) {
				modelAndView.addObject("invalidCredentials", "Invalid username or password");
			}
			return modelAndView;
			
		}
		
		
		this.session.setAttribute("user", user);
		if(this.userService.checkIfUserHaveAhero(validateLoginServiceModel.getUsername()).get().getHero() != null) {
			ValidateCreateHeroModel validateCreateHeroModel = 
					this.modelMapper.map( this.userService.checkIfUserHaveAhero(validateLoginServiceModel.getUsername()).get().getHero(), ValidateCreateHeroModel.class);
			this.session.setAttribute("hero", validateCreateHeroModel);
		}
		
		return new ModelAndView("redirect:/home");
	}
	
	
	@GetMapping("/login")
	public ModelAndView getLogin(@ModelAttribute("loginUser") ValidateLoginServiceModel validateLoginServiceModel, ModelAndView modelAndView) {
		modelAndView.setViewName("userTemplates/login");
		return modelAndView;
	}
	
	
	@GetMapping("/profile")
	public ModelAndView getUserProfile(ModelAndView modelAndView) {
		modelAndView.setViewName("userTemplates/profile");
		String username = ((ValidateLoginServiceModel) this.session.getAttribute("user")).getUsername();
		UserViewModel userViewModel = this.userService.findByUsername(username);
		modelAndView.addObject("myUser", userViewModel);
		return modelAndView;
	}
	
	
	
	
}
