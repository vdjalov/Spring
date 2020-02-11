package app.service.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.data.models.Hero;
import app.data.models.Item;
import app.data.repositories.HeroRepository;
import app.data.repositories.ItemRepository;
import app.service.ItemService;
import app.service.models.RegisterItemServiceModel;
import app.web.models.ItemViewModel;


@Service
public class ItemServiceImpl implements ItemService{

	private ItemRepository itemRepository;
	private HeroRepository heroRepository;
	private ModelMapper modelMapper;
	private HttpSession session;
	
	@Autowired
	public ItemServiceImpl(ItemRepository itemRepository, HeroRepository heroRepository, ModelMapper modelMapper, HttpSession session) {
		this.itemRepository = itemRepository;
		this.heroRepository = heroRepository;
		this.modelMapper = modelMapper;
		this.session = session;
	}

	@Override
	public void save(RegisterItemServiceModel registerItemServiceModel) throws Exception {
		if(this.itemRepository.findByName(registerItemServiceModel.getName()).isPresent()) {
			throw new Exception("Item with such name already exists");
		}
		
		Item item = this.modelMapper.map(registerItemServiceModel, Item.class);
		this.itemRepository.save(item);
	}

	@Override
	public List<ItemViewModel> getAllItems() {
		List<ItemViewModel> allItems = this.itemRepository
											.findAll().stream()
							   			    .map(item -> this.modelMapper.map(item, ItemViewModel.class))
											.collect(Collectors.toList());
		return allItems;
	}


	@Override
	public void findItemByNameAndAddToHeroInventory(String itemName) throws Exception {
		Item currentItem = this.itemRepository.findByName(itemName).get();
		String heroName = (String) this.session.getAttribute("hero"); 
			if(heroName != null) {
				Hero hero = this.heroRepository.findByName(heroName).get();
				Optional<Item> oldItem = hero.getInventory().stream().filter(item -> item.getSlot().equals(currentItem.getSlot())).findFirst();
				
				// Needs to go in a separate method, can be optimized
				if(oldItem.isPresent()) {
					hero.setAttack(hero.getAttack() - oldItem.get().getAttack() + currentItem.getAttack());
					hero.setStrength(hero.getStrength() - oldItem.get().getStrength() + currentItem.getStrength());
					hero.setStamina(hero.getStamina() - oldItem.get().getStamina() + currentItem.getStamina());
					hero.setDefence(hero.getDefence() - oldItem.get().getDefence() + currentItem.getDefence());
				} else {
					hero.setAttack(hero.getAttack() + currentItem.getAttack());
					hero.setStrength(hero.getStrength() + currentItem.getStrength());
					hero.setStamina(hero.getStamina() + currentItem.getStamina());
					hero.setDefence(hero.getDefence() + currentItem.getDefence());
				}
				hero.setInventory(hero.getInventory().stream()
						.filter(item -> !item.getSlot().equals(currentItem.getSlot())).collect(Collectors.toList()));
				hero.getInventory().add(currentItem);
				this.heroRepository.saveAndFlush(hero);
			}else {
				throw new Exception("User does not have a hero. Create hero first.");
			}
		
	}

}
