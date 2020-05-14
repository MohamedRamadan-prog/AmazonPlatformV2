package amazon.layer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	

	@ExceptionHandler(AccessDeniedException.class)
	public ModelAndView handleAccessDenied(AccessDeniedException ex) {

		//Do something additional if required
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("error");
	    modelAndView.addObject("message", "Sorry You Are not Authorized for this Action !!");
	    return modelAndView;

	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ModelAndView handleException(Exception ex)
	{
	    //Do something additional if required
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("error");
	    modelAndView.addObject("message", "OOPs , Something Went Wrong !! , Please Contact Support");
	    return modelAndView;
	}
}