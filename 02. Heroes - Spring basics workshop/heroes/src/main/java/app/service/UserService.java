package app.service;

import javax.validation.Valid;

import app.error.userErros.UserException;
import app.service.models.LoginUserServiceModel;
import app.service.models.RegisterUserServiceModel;

public interface UserService {
	
	public void save(RegisterUserServiceModel registerUserServiceModel) throws UserException;
	public boolean validatePasswords(String password, String confirmPassword);
	public RegisterUserServiceModel validateLogin(@Valid LoginUserServiceModel loginUserServiceModel) throws Exception;
	public boolean validateUsername(String username);
}
