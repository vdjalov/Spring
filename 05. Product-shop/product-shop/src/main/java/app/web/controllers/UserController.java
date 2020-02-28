package app.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller()
@RequestMapping("/users")
public class UserController {

	public static final String USER_PROFILE_VIEW = "userTemplates/profile"; 
	
	
	
	@GetMapping("/profile")
	public ModelAndView getUserProfile(ModelAndView modelAndView) {
		
		
		modelAndView.setViewName(USER_PROFILE_VIEW);
		return modelAndView;
	}
}
