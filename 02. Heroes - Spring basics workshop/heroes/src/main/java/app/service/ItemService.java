package app.service;


import java.util.List;

import app.service.models.RegisterItemServiceModel;
import app.web.models.ItemViewModel;

public interface ItemService {

	public void save(RegisterItemServiceModel registerItemServiceModel);
	public List<ItemViewModel> getAllItems();
}
