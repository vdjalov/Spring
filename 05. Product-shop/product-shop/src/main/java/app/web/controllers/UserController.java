package app.web.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.service.UserService;
import app.web.models.EditUserViewModel;


@Controller()
@RequestMapping("/users")
public class UserController {
	public static final String ALL_USERS_VIEW = "userTemplates/all-users";
	public static final String USER_PROFILE_VIEW = "userTemplates/profile"; 
	public static final String EDIT_PROFILE_VIEW = "userTemplates/edit-profile"; 
	
	private UserService userService;
	
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}


	@GetMapping("/profile")
	public ModelAndView getUserProfile(ModelAndView modelAndView, Principal principal) {
		
		String name = principal.getName().split("[@]+")[0];
		modelAndView.setViewName(USER_PROFILE_VIEW);
		modelAndView.addObject("name", name);
		modelAndView.addObject("email", principal.getName());
			return modelAndView;
	}
	
	
	@GetMapping("/edit")
	public ModelAndView editUserProfile(ModelAndView modelAndView, Principal principal) {
		modelAndView.addObject("email", principal.getName());
		modelAndView.setViewName(EDIT_PROFILE_VIEW);
		return modelAndView;
	}
	
	

	@PostMapping("/edit")
	public ModelAndView commitChangesUserProfile(@ModelAttribute EditUserViewModel editUserViewModel, ModelAndView modelAndView, Principal principal) {
		
		try {
			 this.userService.editUserProfile(editUserViewModel);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("/users/edit");
		}
		
		modelAndView.setViewName("redirect:/home");
		return modelAndView;
	}
	
	
	@GetMapping("/all")
	public ModelAndView getAllUsers(ModelAndView modelAndView) {
		modelAndView.setViewName(ALL_USERS_VIEW);
		
		return modelAndView;		
	}
	
}









