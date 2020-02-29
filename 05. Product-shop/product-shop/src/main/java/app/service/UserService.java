package app.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import app.service.models.RegisterUserServiceModel;
import app.web.models.EditUserViewModel;

public interface UserService extends UserDetailsService {

	RegisterUserServiceModel save(RegisterUserServiceModel registerUserServiceModel);

	EditUserViewModel editUserProfile(EditUserViewModel editUserViewModel) throws Exception;

	
}
