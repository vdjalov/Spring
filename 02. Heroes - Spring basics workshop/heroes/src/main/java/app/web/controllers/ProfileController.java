package app.web.controllers;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.service.UserService;
import app.web.models.UserProfileViewModel;

@Controller
@RequestMapping(value = "/users")
public class ProfileController {

	private UserService userService;
	private ModelMapper modelMapper;
	

	@Autowired
	public ProfileController(UserService userService, ModelMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;
	}


	@GetMapping(value = "/profile")
	public ModelAndView getProfile(ModelAndView modelAndView, HttpSession session) {
		modelAndView.setViewName("profile");
	
		String username = session.getAttribute("username").toString();
		UserProfileViewModel userProfileViewModel = this.modelMapper.map(this.userService.getUserByUsername(username), UserProfileViewModel.class);
		String gender = userProfileViewModel.getHero().getGender().toString().toLowerCase();
		modelAndView.addObject("username", username);
		modelAndView.addObject("email", userProfileViewModel.getEmail());
		modelAndView.addObject("heroName", userProfileViewModel.getHero().getName());
		modelAndView.addObject("genderUrl", "/img/" + gender + ".jpg");
		modelAndView.addObject("hero", userProfileViewModel.getHero().getName());
		return modelAndView;
	}

	
	
}
