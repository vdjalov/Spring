package app.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import app.service.models.LoginUserServiceModel;
import app.service.models.RegisterUserServiceModel;

public interface UserService extends UserDetailsService {

	RegisterUserServiceModel save(RegisterUserServiceModel registerUserServiceModel);
	
}
