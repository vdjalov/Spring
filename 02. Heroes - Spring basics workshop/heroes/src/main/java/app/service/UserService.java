package app.service;

import java.util.Optional;

import app.data.models.User;
import app.service.models.ValidateLoginServiceModel;
import app.service.models.ValidateUserRegisterModel;
import app.web.models.UserViewModel;

public interface UserService {

	ValidateLoginServiceModel save(ValidateLoginServiceModel validateLoginServiceModel);
	boolean validateRegistration(ValidateUserRegisterModel validateUserRegisterModel) throws Exception;
	Optional<User> checkIfUserHaveAhero(String username);
	UserViewModel findByUsername(String username);
	
	
	
}
