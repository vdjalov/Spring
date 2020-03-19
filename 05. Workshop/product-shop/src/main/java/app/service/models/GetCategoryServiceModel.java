package app.service.models;

public class GetCategoryServiceModel {

	private String name;
	
	public GetCategoryServiceModel(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
