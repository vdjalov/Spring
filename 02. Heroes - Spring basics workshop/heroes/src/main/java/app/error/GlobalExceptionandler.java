package app.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionandler {

	@ExceptionHandler()
	public ModelAndView grabError(Exception e) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error");
		modelAndView.addObject("message", e.getMessage()); // Can print message 
		
		return modelAndView;
	}
	
	
}
