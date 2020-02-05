package app.service.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.data.models.Item;
import app.data.repositories.ItemRepository;
import app.service.ItemService;
import app.service.models.RegisterItemServiceModel;
import app.web.models.ItemViewModel;


@Service
public class ItemServiceImpl implements ItemService{

	private ItemRepository itemRepository;
	private ModelMapper modelMapper;
	
	
	@Autowired
	public ItemServiceImpl(ItemRepository itemRepository, ModelMapper modleMapper) {
		this.itemRepository = itemRepository;
		this.modelMapper = modleMapper;
	}

	@Override
	public void save(RegisterItemServiceModel registerItemServiceModel) {
		Item item = this.modelMapper.map(registerItemServiceModel, Item.class);
		this.itemRepository.save(item);
	}

	@Override
	public List<ItemViewModel> getAllItems() {
		List<ItemViewModel> allItems = this.itemRepository.findAll().stream()
														  .map(item -> this.modelMapper.map(item, ItemViewModel.class))
														  .collect(Collectors.toList());
												
		return allItems;
	}

}
