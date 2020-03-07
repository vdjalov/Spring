package app.service.models;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;


public class AddProductServiceModel {

	private String name;
	private String description;
	private double price;
	private MultipartFile image;
	private List<String> categories;
	
	
	public AddProductServiceModel() {}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public MultipartFile getImage() {
		return image;
	}


	public void setImage(MultipartFile image) {
		this.image = image;
	}


	public List<String> getCategories() {
		return categories;
	}


	public void setCategories(List<String> categories) {
		this.categories = categories;
	}


	
	
	
	
	
}
