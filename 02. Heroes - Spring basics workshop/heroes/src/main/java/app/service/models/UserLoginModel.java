package app.service.models;

import app.data.models.Hero;

public class UserLoginModel {

	private String username;
	private String password;
	private Hero hero;
	
	public UserLoginModel() {
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

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}
	
	
	
	
}
