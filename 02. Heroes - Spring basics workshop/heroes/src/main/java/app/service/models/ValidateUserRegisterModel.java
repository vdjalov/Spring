package app.service.models;

import javax.persistence.Column;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import app.validation.FieldMatch;
import lombok.NoArgsConstructor;
@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
})
@NoArgsConstructor
public class ValidateUserRegisterModel {
	
	@NotEmpty(message = "username cannot be empty")
	@Column(unique = true, nullable = false)
	private String username;
	
	@NotEmpty(message = "email cannot be empty")
	@Pattern(regexp = "[a-zA-Z0-9]+@[a-z]+\\.[a-z]{2,3}", message = "Incorrect email")
	private String email;
	
	private String password;
	private String confirmPassword;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
	
	
}
