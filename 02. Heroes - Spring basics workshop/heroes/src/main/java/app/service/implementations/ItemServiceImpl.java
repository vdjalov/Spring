package app.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.data.models.Item;
import app.data.repositories.HeroRepository;
import app.data.repositories.ItemRepository;
import app.service.ItemService;
import app.service.models.ValidateCreateItemModel;

@Service
public class ItemServiceImpl implements ItemService {

	private ItemRepository itemRepository;
	private HeroRepository heroRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public ItemServiceImpl(ItemRepository itemRepository, HeroRepository heroRepository, ModelMapper modelMapper) {
		this.itemRepository = itemRepository;
		this.heroRepository = heroRepository;
		this.modelMapper = modelMapper;
	}


	@Override
	public void saveItem(ValidateCreateItemModel validateCreateItemModel) {
		Item item = this.modelMapper.map(validateCreateItemModel, Item.class);
		this.itemRepository.save(item);
	}

	
	
	
}
