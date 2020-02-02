package demo.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CreateModel {

	
	@NotEmpty(message = "Name should not be empty")
	@Size(min = 3, max = 15, message = "Name should be longer than 3 and shorter than 15 letters")
	@Pattern(regexp = ".*[0-9].*", message = "Name should contain at least one digit")
	private String name;

	@Pattern(regexp = "[a-zA-Z0-9]+@[a-z]+\\.[a-z]{2,3}", message = "Incorrect email")
	private String email;
	
	
	public CreateModel() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
