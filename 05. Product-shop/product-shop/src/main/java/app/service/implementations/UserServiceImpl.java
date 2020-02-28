package app.service.implementations;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.data.models.User;
import app.data.repository.UserRepository;
import app.service.RoleService;
import app.service.UserService;
import app.service.models.RegisterUserServiceModel;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private RoleService roleService;
	private ModelMapper modelMapper;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.roleService = roleService;
		this.modelMapper = modelMapper;
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return this.userRepository.findByEmail(email);
	}


	@Override
	public RegisterUserServiceModel save(RegisterUserServiceModel registerUserServiceModel) {
		
		if(!checkPasswordMatch(registerUserServiceModel)) {
			return null; //if null passwords match validation exception
		}
		checkDbSeed();
		createUser(registerUserServiceModel);
		return registerUserServiceModel;
	}

	
//	Create user and seed in db
	private void createUser(RegisterUserServiceModel registerUserServiceModel) {
		User user = this.modelMapper.map(registerUserServiceModel, User.class);
		if(userRepository.findAll().size() == 0) {
			user.setAuthorities(this.roleService.findAllAuthorities());
		} else {
			user.setAuthorities(new HashSet<>(Set.of(this.roleService.findByAuthority("USER"))));
		}
		this.userRepository.save(user);
	}


//	Seed db if needed
	private void checkDbSeed() {
		if(roleService.getRepositorySize() == 0) {
			this.roleService.seedDb();
		} 
	}


//  Check if password matches confirm password 
	private boolean checkPasswordMatch(RegisterUserServiceModel registerUserServiceModel) {
		return registerUserServiceModel.getPassword().matches(registerUserServiceModel.getConfirmPassword());
	}


}
