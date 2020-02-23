package app.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import app.data.models.User;
import app.service.models.UserRegisterModel;

public interface UserService extends UserDetailsService {

	User register(UserRegisterModel userRegisterModel);

	
}
