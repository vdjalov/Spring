package app.service.implementations;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.data.models.User;
import app.data.repositories.UserRepository;
import app.service.UserService;
import app.service.models.LoginUserServiceModel;
import app.service.models.RegisterUserServiceModel;


@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private ModelMapper modelMapper;
	
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public void save(RegisterUserServiceModel registerUserServiceModel) throws Exception {
		String password = registerUserServiceModel.getPassword();
		String confirmPassword = registerUserServiceModel.getConfirmPassword();
		
		if(this.validatePasswords(password, confirmPassword)) {
			User user = this.modelMapper.map(registerUserServiceModel, User.class);
			this.userRepository.save(user);
		} else {
			throw new Exception("Password does not match confirm password");
		}
		
		
	}

	@Override
	public boolean validatePasswords(String password, String confirmPassword) {
		return password.equals(confirmPassword);
	}

	
	@Override
	public RegisterUserServiceModel validateLogin(@Valid LoginUserServiceModel loginUserServiceModel) throws Exception {
		String username = loginUserServiceModel.getUsername();
		String password = loginUserServiceModel.getPassword();
			if(this.userRepository.findByUsernameAndPassword(username, password).isPresent()) {
				return this.modelMapper.map(this.userRepository.findByUsernameAndPassword(username, password).get(), RegisterUserServiceModel.class);
			} else {
				throw new Exception("Username or password invalid.");
			}
		
	}

}
