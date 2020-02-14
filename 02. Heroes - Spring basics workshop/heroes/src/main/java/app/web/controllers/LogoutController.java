package app.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {

	
	@GetMapping("/logout")
	public ModelAndView logoutUser(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:/");
		
	}
	
}
