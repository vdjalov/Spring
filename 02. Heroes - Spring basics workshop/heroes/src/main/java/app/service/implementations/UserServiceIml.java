package app.service.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.data.models.User;
import app.data.repositories.UserRepository;
import app.service.UserService;
import app.service.models.ValidateLoginServiceModel;
import app.service.models.ValidateUserRegisterModel;


@Service
public class UserServiceIml implements UserService {

	private UserRepository userRepository;
	private ModelMapper modelMapper;
	
	
	@Autowired
	public UserServiceIml(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ValidateLoginServiceModel save(ValidateLoginServiceModel validateLoginServiceModel) {
		String username = validateLoginServiceModel.getUsername();
		String password = validateLoginServiceModel.getPassword();
			if(this.userRepository.findByUsernameAndPassword(username, password).isPresent()) {
				return validateLoginServiceModel;
			}
		
		return null;
	}

	@Override
	public boolean validateRegistration(ValidateUserRegisterModel validateUserRegisterModel) throws Exception {
		String username = validateUserRegisterModel.getUsername();
		String password = validateUserRegisterModel.getPassword();
		String confirmPassword = validateUserRegisterModel.getConfirmPassword();
		
		if(validatePasswords(password, confirmPassword)) {
			if(this.userRepository.findByUsername(username).isPresent()) {
				throw new Exception("user with this username already exists");
			}
		} else {
			throw new Exception("password does not match confirm password");
		}
		
		User user = this.modelMapper.map(validateUserRegisterModel, User.class);
		this.userRepository.save(user);
		
		return true;
	}

	
	
	private boolean validatePasswords(String password, String confirmPassword) {
		return password.equals(confirmPassword);
	}

	
	
	
	
}
