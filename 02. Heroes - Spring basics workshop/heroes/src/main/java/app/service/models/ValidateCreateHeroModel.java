package app.service.models;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import app.data.models.User;
import app.data.models.enums.Gender;

public class ValidateCreateHeroModel {

	@NotEmpty(message = "name cannot be empty")
	@Column(unique = true)
	private String name;
	
	@NotNull(message = "gender cannot be empty")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Min(value = 0, message = "min level should be (zero)")
	private int level;
	
	@Min(value = 0, message = "min level should be (zero)")
	private int stamina;
	
	@Min(value = 0, message = "min level should be (zero)")
	private int strength;
	
	@Min(value = 0, message = "min level should be (zero)")
	private int attack;
	
	@Min(value = 0, message = "min level should be (zero)")
	private int defence;

	private User user;

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

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
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
	
	
	
	
}






