package app.apiTests;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import app.data.models.Item;
import app.data.models.enums.Slot;
import app.data.repositories.ItemRepository;
import app.web.models.ItemViewModel;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemsApiControllerTest {

	@MockBean
	ItemRepository itemRepository;
	
	@Autowired
	TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	
	@Test
	public void getItemsApiTest() {
		Item item = new Item(); 
		item.setName("boots");
		item.setSlot(Slot.PADS);
		
		List<Item> allItems = new ArrayList<Item>();
		allItems.add(item);
		allItems.add(item);
		
		
		when(itemRepository.findAll()).thenReturn(allItems);
	
		ResponseEntity<ItemViewModel[]> result = restTemplate.getForEntity("http://localhost:" + port + "/api/items", ItemViewModel[].class);
		
		assertTrue(result.getBody()[0].getName().equals(item.getName()));
		assertTrue(result.getBody()[0].getSlot().toString().equals(item.getSlot().toString()));
	}
}















