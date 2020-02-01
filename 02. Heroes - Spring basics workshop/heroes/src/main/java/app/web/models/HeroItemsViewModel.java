package app.web.models;

import app.data.models.Hero;
import app.data.models.Item;

public class HeroItemsViewModel {

	private Hero hero;
	private Item item;
	
	public HeroItemsViewModel() {
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
