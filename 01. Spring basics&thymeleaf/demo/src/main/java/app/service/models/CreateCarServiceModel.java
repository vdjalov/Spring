package app.service.models;


public class CreateCarServiceModel {

	
	private String brand;
	private String model;
	private Integer year;
	private String engine;
	
	
	public CreateCarServiceModel() {	
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public Integer getYear() {
		return year;
	}


	public void setYear(Integer year) {
		this.year = year;
	}


	public String getEngine() {
		return engine;
	}


	public void setEngine(String engine) {
		this.engine = engine;
	}
	
	
	
	
	
}
