package app.error.userErros;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE,reason = "Username already exists.")
public class UserException extends Exception {

	public UserException(String message) {
		super(message);
		
	}

	

	

}
