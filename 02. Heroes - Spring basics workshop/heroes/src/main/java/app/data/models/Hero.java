package app.data.models;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import app.data.enums.Gender;

@Entity
@Table(name = "heroes")
public class Hero extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Gender gender;
	
	@Column(nullable = false)
	private Integer level;
	
	@Column(nullable = false)
	private Integer stamina;
	
	@Column(nullable = false)
	private Integer attack;
	
	@Column(nullable = false)
	private Integer defence;
		
	@OneToMany(targetEntity = Item.class)
	@JoinColumn(name = "item_id", referencedColumnName = "id")
	private List<Item> inventory;
	
	@OneToOne(targetEntity = User.class, mappedBy = "hero")
	private User user;
	
	public Hero() {
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
