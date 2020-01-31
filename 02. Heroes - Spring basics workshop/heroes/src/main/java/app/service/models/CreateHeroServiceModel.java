package app.service.models;

import java.util.ArrayList;
import java.util.List;

import app.data.enums.Gender;
import app.data.models.Item;
import app.data.models.User;

public class CreateHeroServiceModel {

	private String name;
	private Gender gender;
	private Integer level;
	private Integer stamina;
	private Integer attack;
	private Integer defence;
	private List<Item> inventory;
	private User user;
	
	
	public CreateHeroServiceModel() {
		this.inventory = new ArrayList<>();
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


	public Integer getLevel() {
		return level;
	}


	public void setLevel(Integer level) {
		this.level = level;
	}


	public Integer getStamina() {
		return stamina;
	}


	public void setStamina(Integer stamina) {
		this.stamina = stamina;
	}


	public Integer getAttack() {
		return attack;
	}


	public void setAttack(Integer attack) {
		this.attack = attack;
	}


	public Integer getDefence() {
		return defence;
	}


	public void setDefence(Integer defence) {
		this.defence = defence;
	}


	public List<Item> getInventory() {
		return inventory;
	}


	public void setInventory(List<Item> inventory) {
		this.inventory = inventory;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	
	
	
	
	
	
}
