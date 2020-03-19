package app.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import app.service.UserService;
import app.service.models.UserServiceModel;

@RestController
@RequestMapping(value = "/api")
public class UserApiController {

	
	private UserService userService;

	
	@Autowired
	public UserApiController(UserService userService) {
		this.userService = userService;
	}
	
	
	@GetMapping(value = "/allUsers", produces = "application/json")
	public List<UserServiceModel> getAllUsers() {
		List<UserServiceModel> allUsers = this.userService.getUsers();
		return allUsers;
	}
	
	
	@PostMapping("/users/set-role/{role}/{email}")
	public ModelAndView updateRoleToModerator(@PathVariable("role") String role, @PathVariable("email") String email) {
		
		this.userService.updateUserAuthority(role, email);
		return new ModelAndView("redirect:/users/all");
	}
	
}
