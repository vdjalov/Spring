package app.service.models;

import javax.validation.constraints.NotEmpty;

public class LoginUserServiceModel {

	@NotEmpty(message = "Username cannot be empty")
	private String username;
	
	@NotEmpty(message = "Password cannot be empty")
	private String password;
	
	public LoginUserServiceModel() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
