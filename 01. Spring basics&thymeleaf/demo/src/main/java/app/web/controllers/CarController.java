package app.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import app.service.implementations.CarService;
import app.service.models.CreateCarServiceModel;

@Controller
public class CarController {

	private CarService carService;
	
	@Autowired
	public CarController(CarService carService) {
		this.carService = carService;
	}

	@GetMapping(value = "/all")
	public ModelAndView all(ModelAndView modelAndView) {
		List<CreateCarServiceModel> allCars = this.carService.getAllCars();
		modelAndView.addObject("cars", allCars);
		modelAndView.setViewName("all");
		return modelAndView;
	}
	

	@GetMapping(value = "/create")
	public ModelAndView create(ModelAndView modelAndView) {
		modelAndView.setViewName("create");
		return modelAndView;
	}
	
	
	@PostMapping(value = "/create")
	public ModelAndView createCar(@ModelAttribute CreateCarServiceModel serviceCar) {
		this.carService.save(serviceCar);
		return new ModelAndView("redirect:/all");
	}
	
	
	
	
}
