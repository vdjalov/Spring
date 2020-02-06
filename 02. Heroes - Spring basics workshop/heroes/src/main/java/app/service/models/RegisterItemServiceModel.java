package app.service.models;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import app.data.models.enums.Slot;

public class RegisterItemServiceModel {

	@NotEmpty(message = "Name cannot be empty")
	private String name;
	
	private Slot slot;
	
	@Min(value = 1)
	private int stamina;
	
	@Min(value = 1)
	private int strength;
	
	@Min(value = 1)
	private int attack;
	
	@Min(value = 1)
	private int defence;

	public RegisterItemServiceModel() {
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
