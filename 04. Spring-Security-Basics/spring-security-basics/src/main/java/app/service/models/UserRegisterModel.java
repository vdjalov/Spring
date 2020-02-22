package app.service.models;

import java.util.Set;

public class UserRegisterModel {

	private String username;
	private String password;
	private String confirmPassword;
	private Set<RoleServiceModel> authorities;
	
	public UserRegisterModel() {
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


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	public Set<RoleServiceModel> getAuthorities() {
		return authorities;
	}


	public void setAuthorities(Set<RoleServiceModel> authorities) {
		this.authorities = authorities;
	}
	
	
	
	
}
