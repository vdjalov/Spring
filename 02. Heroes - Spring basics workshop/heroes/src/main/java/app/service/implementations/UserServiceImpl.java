package app.service.implementations;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.data.models.User;
import app.data.repositories.UserRepository;
import app.service.UserService;
import app.service.models.UserLoginModel;
import app.service.models.UserProfileServiceModel;
import app.service.models.UserRegisterModel;
import app.utils.UserUtils;


@Service
public class UserServiceImpl implements UserService {

	
	private ModelMapper modelMapper;
	private UserRepository userRepository;
	private UserUtils userUtils;
	
	
	@Autowired
	public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, UserUtils userUtils) {
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
		this.userUtils = userUtils;
	}


	@Override
	public boolean save(UserRegisterModel userRegisterModel) {
			if(this.validateUserPassword(userRegisterModel.getPassword(), userRegisterModel.getConfirmPassword())) {
				
				userRegisterModel.setPassword(this.userUtils.hashPassword(userRegisterModel.getPassword()));
				User user = this.modelMapper.map(userRegisterModel, User.class);
				this.userRepository.save(user);
				return true;
			} 
			return false;
	}
	
	
	private boolean validateUserPassword(String password, String confirmPassword) {
		return password.equals(confirmPassword);
	}


	@Override
	public UserLoginModel verifyLogin(UserLoginModel userLoginModel) {
		Optional<User> user = this.userRepository.findByUsernameAndPassword(userLoginModel.getUsername(), this.userUtils.hashPassword(userLoginModel.getPassword()));
			if(user.isPresent()) {
				return this.modelMapper.map(user.get(), UserLoginModel.class);
			}
	
		return null;
	}


	@Override
	public UserProfileServiceModel getUserByUsername(String username) {
		return this.modelMapper.map(this.userRepository.findByUsername(username).get(), UserProfileServiceModel.class);
	}

}













