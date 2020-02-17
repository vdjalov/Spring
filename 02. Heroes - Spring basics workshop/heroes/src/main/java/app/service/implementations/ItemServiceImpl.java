package app.service.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.data.models.Hero;
import app.data.models.Item;
import app.data.repositories.HeroRepository;
import app.data.repositories.ItemRepository;
import app.service.ItemService;
import app.service.models.ValidateCreateHeroModel;
import app.service.models.ValidateCreateItemModel;
import app.service.session.SessionService;
import app.web.models.ItemViewModel;

@Service
public class ItemServiceImpl implements ItemService {

	private ItemRepository itemRepository;
	private HeroRepository heroRepository;
	private ModelMapper modelMapper;
	private SessionService session;
	
	
	@Autowired
	public ItemServiceImpl(ItemRepository itemRepository, HeroRepository heroRepository, SessionService session, ModelMapper modelMapper) {
		this.itemRepository = itemRepository;
		this.heroRepository = heroRepository;
		this.session = session;
		this.modelMapper = modelMapper;
	}


	@Override
	public void saveItem(ValidateCreateItemModel validateCreateItemModel) {
		Item item = this.modelMapper.map(validateCreateItemModel, Item.class);
		this.itemRepository.save(item);
	}


	@Override
	public List<ItemViewModel> getAllItems() {
		return this.itemRepository.findAll().stream()
								  .map(item -> this.modelMapper.map(item, ItemViewModel.class))
								  .collect(Collectors.toList());
	}


	@Override
	public void buyItem(String name) {
		String heroName = ((ValidateCreateHeroModel) this.session.getSessionAttribute("hero")).getName();
		Item item = this.itemRepository.findByName(name).get();
		Hero hero = this.heroRepository.findByName(heroName).get();
		hero = addItem(hero, item);
		
		
		item.setOwned(true);
		
		this.itemRepository.save(item); // updating owned column
		this.heroRepository.save(hero); // updating hero 
	}


//	calculates hero stats
	private Hero addItem(Hero hero, Item item) {
		List<Item> updatedInventory = hero.getInventory()
										 .stream()
										 .filter(currentItem -> !currentItem.getSlot().toString().equals(item.getSlot().toString()))
										 .collect(Collectors.toList());
		updatedInventory.add(item);
		hero.setInventory(updatedInventory);
		hero.setAttack(updatedInventory.stream().mapToInt(i -> item.getAttack()).sum());
		hero.setDefence(updatedInventory.stream().mapToInt(i -> item.getDefence()).sum());
		hero.setStamina(updatedInventory.stream().mapToInt(i -> item.getStamina()).sum());
		hero.setStrength(updatedInventory.stream().mapToInt(i -> item.getStrength()).sum());
		
		return hero;
	}

	
	
	
	
	
}
