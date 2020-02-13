package app.service;

import app.service.models.ValidateLoginServiceModel;
import app.service.models.ValidateUserRegisterModel;

public interface UserService {

	ValidateLoginServiceModel save(ValidateLoginServiceModel validateLoginServiceModel);
	boolean validateRegistration(ValidateUserRegisterModel validateUserRegisterModel) throws Exception;
	
	
	
}
