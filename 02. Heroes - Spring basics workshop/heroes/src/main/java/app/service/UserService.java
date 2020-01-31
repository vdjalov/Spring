package app.service;

import app.service.models.UserLoginModel;
import app.service.models.UserProfileServiceModel;
import app.service.models.UserRegisterModel;

public interface UserService {

	public boolean save(UserRegisterModel userRegisterModel);

	public UserLoginModel verifyLogin(UserLoginModel userLoginModel);

	public UserProfileServiceModel getUserByUsername(String username);
	
}
