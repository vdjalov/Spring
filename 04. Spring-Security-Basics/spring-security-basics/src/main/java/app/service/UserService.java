package app.service;


import app.data.models.User;
import app.service.models.UserRegisterModel;

public interface UserService {

	User register(UserRegisterModel userRegisterModel);

	
}
