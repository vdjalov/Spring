package app.service;


import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import app.service.models.RegisterUserServiceModel;
import app.service.models.UserServiceModel;
import app.web.models.EditUserViewModel;

public interface UserService extends UserDetailsService {

	RegisterUserServiceModel save(RegisterUserServiceModel registerUserServiceModel);

	EditUserViewModel editUserProfile(EditUserViewModel editUserViewModel) throws Exception;

	List<UserServiceModel> getUsers();

	
}
