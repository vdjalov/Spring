package app.service.models;

import app.data.models.Hero;

public class UserProfileServiceModel {

	private String username;
	private String email;
	private Hero hero;
	
	
	public UserProfileServiceModel() {
	}


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


	public Hero getHero() {
		return hero;
	}


	public void setHero(Hero hero) {
		this.hero = hero;
	}
}
