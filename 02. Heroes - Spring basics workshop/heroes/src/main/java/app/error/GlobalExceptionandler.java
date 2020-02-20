package app.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class GlobalExceptionandler {

	
	@ExceptionHandler({Exception.class})
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ModelAndView grabError(HttpServletRequest req, Exception e) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error");
		String reqUrl = req.getRequestURI();
		modelAndView.addObject("message", e.getMessage()); // Can print message 
		modelAndView.addObject("url", reqUrl);
	
		return modelAndView;
	}
	
	
}
