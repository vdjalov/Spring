package app.service.implementations;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.data.models.User;
import app.data.repositories.UserRepository;
import app.error.userError.UserException;
import app.hashing.HashingService;
import app.service.UserService;
import app.service.models.LoginUserServiceModel;
import app.service.models.RegisterUserServiceModel;


@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private ModelMapper modelMapper;
	private HashingService hashingService;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, HashingService hashingService) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.hashingService = hashingService;
	}

	@Override
	public void save(RegisterUserServiceModel registerUserServiceModel) throws UserException {
		String password = registerUserServiceModel.getPassword();
		String confirmPassword = registerUserServiceModel.getConfirmPassword();
		
		if(this.validatePasswords(password, confirmPassword)) {
			if(this.validateUsername(registerUserServiceModel.getUsername())) {
				throw new UserException("Username is already taken");
			}
			registerUserServiceModel.setPassword(this.hashingService.hashPassword(password));
			User user = this.modelMapper.map(registerUserServiceModel, User.class);
			this.userRepository.save(user);
		} else {
			throw new UserException("Password does not match confirm password");
		}
		
		
	}

	@Override
	public boolean validateUsername(String username) {
		return this.userRepository.findByUsername(username).isPresent();
	
	}

	@Override
	public boolean validatePasswords(String password, String confirmPassword) {
		return password.equals(confirmPassword);
	}

	
	@Override
	public RegisterUserServiceModel validateLogin(@Valid LoginUserServiceModel loginUserServiceModel) throws UserException {
		String username = loginUserServiceModel.getUsername();
		String password = this.hashingService.hashPassword(loginUserServiceModel.getPassword());
			if(this.userRepository.findByUsernameAndPassword(username, password).isPresent()) {
				return this.modelMapper.map(this.userRepository.findByUsernameAndPassword(username, password).get(), RegisterUserServiceModel.class);
			} else {
				throw new UserException("Username or password invalid.");
			}
		
	}

	

}
