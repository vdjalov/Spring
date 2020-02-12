package app;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import app.data.models.Item;
import app.data.models.enums.Slot;
import app.data.repositories.HeroRepository;
import app.data.repositories.ItemRepository;
import app.service.ItemService;
import app.service.implementations.ItemServiceImpl;
import app.service.models.RegisterItemServiceModel;

@SpringBootTest
public class ItemServiceTest {

	
	ItemService itemService;
	
	@Mock ItemRepository itemRepository;
	@Mock HeroRepository heroRepository;
	ModelMapper modelMapper;
	@Mock HttpSession session;
	
	
	
	@BeforeEach
	public void init() {
		modelMapper = new ModelMapper();
		itemService = new ItemServiceImpl(itemRepository, heroRepository, modelMapper, session);
	}
	
	
	@Test
	void testSaveThrowExceptionIfItemWithSuchNameAlreadyExists() {
		Item item = new Item();
		Mockito.when(itemRepository.findByName(Mockito.anyString()))
			   .thenReturn(Optional.of(item));
		
		RegisterItemServiceModel registerItemServiceModel = new RegisterItemServiceModel();
		registerItemServiceModel.setName("OK");
		
		boolean thrown = false;
		try {
			itemService.save(registerItemServiceModel);
		} catch (Exception e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}
	
	
	@Test
	void testSaveItemFunctionality() throws Exception {
		Item item = new Item();
		item.setAttack(1);
		item.setDefence(1);
		item.setName("ok");
		item.setSlot(Slot.PADS);
		item.setStamina(1);
		item.setStrength(1);
		
		Mockito.when(itemRepository.findByName(Mockito.anyString()))
			   .thenReturn(null);
		
		Mockito.when(itemRepository.save(Mockito.any()))
		   .thenReturn(item);
		
		itemService.save(new RegisterItemServiceModel());
		
		verify(itemRepository, times(1)).save(Mockito.any(Item.class)); // verify save method was invoked 
	
		assertEquals("saved",itemRepository.save(item).getName(), "ok"); // Result as it should be
	}
	
	
	
}
