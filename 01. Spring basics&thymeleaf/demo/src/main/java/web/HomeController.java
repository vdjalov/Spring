package web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

	@GetMapping(value = "/")
	public String home() {
		return "home";
	}
	
	
	@GetMapping(value = "/create")
	public String create() {
		return "create";
	}
	
	
	@GetMapping(value = "/all")
	public String all() {
		return "all";
	}
	
}
