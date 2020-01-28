package app.data.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "hero_items")
public class HeroItems extends BaseEntity{

	@OneToOne(targetEntity = HeroItems.class)
	@JoinColumn(name = "hero_id", referencedColumnName = "id")
	private Hero hero;
	
	@OneToOne(targetEntity = HeroItems.class)
	@JoinColumn(name = "item_id", referencedColumnName = "id")
	private Item item;
	
	
	public HeroItems() {
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}
	
}
