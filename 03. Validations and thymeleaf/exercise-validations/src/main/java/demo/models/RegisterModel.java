package demo.models;

import demo.annotations.UserRegisterValidation;

public class RegisterModel {

	@UserRegisterValidation
	private String name;

	public RegisterModel() {}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
