package app.web.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Common {

	@GetMapping(value = "/home")
	public ModelAndView loadProducts(@RequestParam Optional<Integer> page, ModelAndView modelAndView) {
	
		modelAndView.setViewName("home");
		if(page.isPresent()) {
			modelAndView.addObject("page", page); // TRY passing this to JS
		}
		
		return modelAndView;
	}
	
	
}
