package app.web.models;

import app.data.models.Hero;

public class UserProfileViewModel {

	private String username;
	private String email;
	private Hero hero;
	
	
	public UserProfileViewModel() {
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
