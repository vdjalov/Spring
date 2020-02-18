package app.data.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import app.data.models.enums.Gender;


@Entity
@Table(name = "heroes")
public class Hero extends BaseEntity {

	@NotEmpty(message = "name cannot be empty")
	@Column(unique = true)
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Min(value = 0, message = "min level should be 0(zero)")
	private int level;
	
	@Min(value = 0, message = "min level should be 0(zero)")
	private int stamina;
	
	@Min(value = 0, message = "min level should be 0(zero)")
	private int strength;
	
	@Min(value = 0, message = "min level should be 0(zero)")
	private int attack;
	
	@Min(value = 0, message = "min level should be 0(zero)")
	private int defence;
	
	@ManyToMany(targetEntity = Item.class)
	@JoinTable(name = "hero_item",
		joinColumns = @JoinColumn(name = "hero_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"))
	private List<Item> inventory;
	
	@OneToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
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




