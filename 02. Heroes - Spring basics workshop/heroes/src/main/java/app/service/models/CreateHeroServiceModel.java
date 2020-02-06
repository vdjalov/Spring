package app.service.models;



import javax.validation.constraints.NotEmpty;

import app.data.models.User;
import app.data.models.enums.Gender;

public class CreateHeroServiceModel {


	@NotEmpty(message = "Name cannot be empty")
	private String name;
	private Gender gender;
	private int level;
	private int strength;
	private int stamina;
	private int attack;
	private int defence;
	private User user;
	
	public CreateHeroServiceModel() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}
	
	
	
	
}
