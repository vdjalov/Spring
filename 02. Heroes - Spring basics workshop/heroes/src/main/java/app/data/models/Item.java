package app.data.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import app.data.models.enums.Slot;

@Entity
@Table(name = "items")
public class Item extends BaseModel {

	@NotEmpty(message = "Item name cannot be empty")
	@Column(nullable = false, unique = true)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column()
	private Slot slot;
	
	@Column()
	private int stamina;
	
	@Column()
	private int strength;
	
	@Column()
	private int attack;
	
	@Column()
	private int defence;

	public Item() {
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
	
	
	
	
	
}
