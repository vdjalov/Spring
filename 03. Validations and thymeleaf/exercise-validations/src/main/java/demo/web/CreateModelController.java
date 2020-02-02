package demo.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import demo.models.CreateModel;

@Controller()
public class CreateModelController {

	@ModelAttribute("createModel")
	public CreateModel createModel() {
		return new CreateModel();
	}
	
	
	
	@GetMapping("/create")
	public String create(@ModelAttribute("createModel") CreateModel createModel) {
		return "create-user";
		
	}
	
	
	@PostMapping("/create")
	public String save(@Valid @ModelAttribute("createModel") CreateModel createModel, 
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "create-user";
		}
		
		return "redirect:/";
	}
	
}
