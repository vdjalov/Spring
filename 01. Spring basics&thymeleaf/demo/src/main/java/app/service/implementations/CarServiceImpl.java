package app.service.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.data.models.Car;
import app.data.repositories.CarRepository;
import app.service.models.CreateCarServiceModel;

@Service
public class CarServiceImpl implements CarService{

	private CarRepository carRepository;
	private ModelMapper modelMapper;
	
	
	@Autowired
	public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper) {
		this.carRepository = carRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public void save(CreateCarServiceModel carServiceModel) {
		Car car = this.modelMapper.map(carServiceModel, Car.class);
		this.carRepository.saveAndFlush(car);
	}

	@Override
	public List<CreateCarServiceModel> getAllCars() {
	return (List<CreateCarServiceModel>) this.carRepository.findAll().stream()
						  .map(car -> this.modelMapper.map(car, CreateCarServiceModel.class))
						  .collect(Collectors.toList());
	}

}
