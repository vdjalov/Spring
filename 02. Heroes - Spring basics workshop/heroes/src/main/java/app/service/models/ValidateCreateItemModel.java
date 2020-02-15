package app.service.models;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import app.data.models.enums.Slot;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ValidateCreateItemModel {

	
	@NotEmpty(message = "name cannot be empty")
	private String name;
	
	@NotNull(message = "you have to choose armor or weapon")
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
