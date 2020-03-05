package app.web.models;

public class CategoryViewModel {

	private Integer id;
	private String name;
	
	public CategoryViewModel() {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
