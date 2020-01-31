package app.data.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hero_items")
public class HeroItems extends BaseEntity{

	@ManyToOne(targetEntity = Hero.class)
	@JoinColumn(name = "hero_id", referencedColumnName = "id")
	private Hero hero;
	
	@ManyToOne(targetEntity = Item.class)
	@JoinColumn(name = "item_id", referencedColumnName = "id")
	private Item item;
	
	
	public HeroItems() {
	}


	public Hero getHero() {
		return hero;
	}


	public void setHero(Hero hero) {
		this.hero = hero;
	}


	public Item getItem() {
		return item;
	}


	public void setItem(Item item) {
		this.item = item;
	}


	


	


	

	
	
}
