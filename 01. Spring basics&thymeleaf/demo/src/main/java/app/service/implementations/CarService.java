package app.service.implementations;


import java.util.List;

import app.service.models.CreateCarServiceModel;


public interface CarService {

	void save(CreateCarServiceModel carServiceModel);
	List<CreateCarServiceModel> getAllCars();
}
