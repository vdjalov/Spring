package demo.web;


import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class TestController {

	
	@GetMapping("/")
	public ModelAndView getIndex(ModelAndView modelAndView) {
		modelAndView.setViewName("index");
		modelAndView.addObject("dateTime", new Date());
		modelAndView.addObject("name", "Vladimir");
		double[] prices = {22.22, 22.13, 23.23};
		modelAndView.addObject("prices", prices);
		
		
		
		return modelAndView;
	}
	
	
}
