package app.service.implementations;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import app.data.models.Role;
import app.data.models.User;
import app.data.repository.UserRepository;
import app.service.RoleService;
import app.service.UserService;
import app.service.models.EditUserSeviceModel;
import app.service.models.RegisterUserServiceModel;
import app.service.models.UserServiceModel;
import app.web.models.EditUserViewModel;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private RoleService roleService;
	private ModelMapper modelMapper;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleService = roleService;
		this.modelMapper = modelMapper;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
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


	@Override
	public EditUserViewModel editUserProfile(EditUserViewModel editUserViewModel) throws Exception {
		EditUserSeviceModel editUserSeviceModel = this.modelMapper.map(editUserViewModel, EditUserSeviceModel.class);
		User user = (User) this.userRepository.findByEmail(editUserSeviceModel.getEmail());
		
		if(!this.bCryptPasswordEncoder.matches(editUserSeviceModel.getOldPassword(), user.getPassword())) {
			throw new Exception("Old password does not match reference");
		}
		String newPassword = this.bCryptPasswordEncoder.encode(editUserSeviceModel.getPassword());
		user.setPassword(newPassword);
		this.userRepository.save(user);
		return editUserViewModel;
	}


	@Override
	public List<UserServiceModel> getUsers() {
				
		List<UserServiceModel> users = this.userRepository.findAll().stream()
						   .map(user -> this.modelMapper.map(user, UserServiceModel.class))
						   .collect(Collectors.toList());
		 return users;
	}


	@Override
	public void updateUserAuthority(String role, String email) {
		User user = (User) this.loadUserByUsername(email);
		Role currentRole = roleService.findByAuthority(role.toUpperCase()); // Add models
		Set<Role> authorities = (Set<Role>) user.getAuthorities();
		authorities.add(currentRole);
		user.setAuthorities(authorities);
		
		this.userRepository.save(user);
		
	}


}
