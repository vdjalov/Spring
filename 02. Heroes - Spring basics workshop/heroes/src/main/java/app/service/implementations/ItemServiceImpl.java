package app.service.implementations;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.data.models.HeroItems;
import app.data.models.Item;
import app.data.repositories.HeroRepository;
import app.data.repositories.ItemRepository;
import app.data.repositories.MerchantRepository;
import app.service.ItemService;
import app.service.models.CreateItemServiceModel;


@Service
public class ItemServiceImpl implements ItemService {

	private ItemRepository itemRepository;
	private HeroRepository heroRepository;
	private ModelMapper modelMapper;
	private HttpSession httpSession;
	private MerchantRepository merchentRepository;
	
	
	@Autowired
	public ItemServiceImpl(ItemRepository itemRepository, HeroRepository heroRepository, ModelMapper modelMapper, HttpSession httpSession, MerchantRepository merchantRepository) {
		this.itemRepository = itemRepository;
		this.modelMapper = modelMapper;
		this.heroRepository = heroRepository;
		this.httpSession = httpSession;
		this.merchentRepository = merchantRepository;
	}



	@Override
	public void save(CreateItemServiceModel createItemServiceModel) {
		Item item = this.modelMapper.map(createItemServiceModel, Item.class);
		Object heroName = httpSession.getAttribute("hero");
	
			item.setHero(this.heroRepository.findByName(heroName.toString()).get());
			this.httpSession.setAttribute("hero", heroName.toString());
			HeroItems heroItems = new HeroItems();
			heroItems.setHero(this.heroRepository.findByName(heroName.toString()).get());
			heroItems.setItem(item);
			this.httpSession.setAttribute("username", httpSession.getAttribute("username"));
			this.httpSession.setAttribute("hero", heroName.toString());
		
		this.httpSession.setAttribute("username", httpSession.getAttribute("username"));
		this.itemRepository.saveAndFlush(item);
		this.merchentRepository.saveAndFlush(heroItems);
		
	}

}








