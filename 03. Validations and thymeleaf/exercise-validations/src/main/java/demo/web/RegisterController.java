package demo.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import demo.models.RegisterModel;

@Controller
public class RegisterController {

	@ModelAttribute("registerUser")
	public RegisterModel registerUser() {
		return new RegisterModel();
	}
	
	@GetMapping("/register")
	public String register(@ModelAttribute("registerUser") RegisterModel registerModel) {
		return "register-user";
	}
	
	@PostMapping("/register")
	public String createUser(@Valid @ModelAttribute("registerUser") RegisterModel registerModel,
			BindingResult bindingResult) {
		
			if(bindingResult.hasErrors()) {
				return "register-user";
			}
				return "redirect:/";
	}
	
	
	
	
}
