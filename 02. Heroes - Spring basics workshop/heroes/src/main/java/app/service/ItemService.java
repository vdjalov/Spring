package app.service;

import java.util.List;

import app.service.models.ValidateCreateItemModel;
import app.web.models.ItemViewModel;

public interface ItemService {

	public void saveItem(ValidateCreateItemModel validateCreateItemModel);

	public List<ItemViewModel> getAllItems();

	public void buyItem(String name);
	
	
}
