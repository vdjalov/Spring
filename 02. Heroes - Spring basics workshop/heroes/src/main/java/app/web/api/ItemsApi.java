package app.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.service.ItemService;
import app.web.models.ItemViewModel;
import lombok.NoArgsConstructor;



@RestController
@NoArgsConstructor
public class ItemsApi {

	private final ItemService itemsService;
    
    
    @Autowired
	public ItemsApi(ItemService itemsService) {
		this.itemsService = itemsService;
	}
	
    
    @GetMapping(value = "/api/items", produces = "application/json")
    public ResponseEntity<List<ItemViewModel>>getAllItems() {
    	List<ItemViewModel> allItems = this.itemsService.getAllItems();
    	
    	return new ResponseEntity<>(allItems, HttpStatus.OK);
    }
	
    
	
}
