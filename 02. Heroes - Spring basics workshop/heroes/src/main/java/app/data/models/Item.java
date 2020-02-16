package app.data.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import app.data.models.enums.Slot;

@Entity
@Table(name = "items")
public class Item extends BaseEntity {

	@NotEmpty(message = "name cannot be empty")
	private String name;
	
	@NotNull(message = "slot cannot be empty")
	@Enumerated(EnumType.STRING)
	private Slot slot;
	
	@Min(value = 0, message = "stamina cannot be 0(zero)")
	private int stamina;
	
	@Min(value = 0, message = "stamina cannot be 0(zero)")
	private int strength;
	
	@Min(value = 0, message = "stamina cannot be 0(zero)")
	private int attack;
	
	@Min(value = 0, message = "stamina cannot be 0(zero)")
	private int defence;
	
	@Column(updatable = true)
	private boolean isOwned;
	
	@ManyToMany(targetEntity = Hero.class, mappedBy = "inventory", cascade = CascadeType.ALL)
	private List<Hero> heroes;

	public Item() {
		this.heroes = new ArrayList<Hero>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
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

	public List<Hero> getHeroes() {
		return heroes;
	}

	public void setHeroes(List<Hero> heroes) {
		this.heroes = heroes;
	}

	public boolean isOwned() {
		return isOwned;
	}

	public void setOwned(boolean isOwned) {
		this.isOwned = isOwned;
	}
	
	
	
}
