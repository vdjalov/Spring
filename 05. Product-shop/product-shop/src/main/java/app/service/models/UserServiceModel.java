package app.service.models;

import java.util.Set;

import app.data.models.Role;

public class UserServiceModel {

	private String id;
	private String email;
	private Set<Role> authorities;
	
	public UserServiceModel() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	
}
