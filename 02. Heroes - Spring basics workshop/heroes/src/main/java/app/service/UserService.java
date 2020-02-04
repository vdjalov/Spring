package app.service;

import javax.validation.Valid;

import app.service.models.LoginUserServiceModel;
import app.service.models.RegisterUserServiceModel;

public interface UserService {
	
	public void save(RegisterUserServiceModel registerUserServiceModel) throws Exception;
	public boolean validatePasswords(String password, String confirmPassword);
	public RegisterUserServiceModel validateLogin(@Valid LoginUserServiceModel loginUserServiceModel) throws Exception;
}
