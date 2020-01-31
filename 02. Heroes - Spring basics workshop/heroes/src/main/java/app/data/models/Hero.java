package app.data.models;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import app.data.enums.Gender;

@Entity
@Table(name = "heroes")
public class Hero extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Gender gender;
	
	@Min(value = 0)
	@Column(columnDefinition = "int default 0")
	private int level;
	
	@Min(value = 0)
	@Column(columnDefinition = "int default 0")
	private int strength;
	
	@Min(value = 0)
	@Column(columnDefinition = "int default 0")
	private int stamina;
	
	@Min(value = 0)
	@Column(columnDefinition = "int default 0")
	private int attack;
	
	@Min(value = 0)
	@Column(columnDefinition = "int default 0")
	private int defence;
		
	@OneToMany(targetEntity = Item.class, mappedBy = "hero")
	private List<Item> inventory;
	
	@OneToOne(targetEntity = User.class, mappedBy = "hero", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private User user;
	
	public Hero() {
		this.inventory = new ArrayList<Item>();
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

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
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
