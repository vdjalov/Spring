package app.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import app.data.models.User;
import app.data.repository.RoleRepository;
import app.data.repository.UserRepository;
import app.service.models.UserRegisterModel;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private RoleService roleService;
	private RoleRepository roleRepository;
	private ModelMapper modelMapper;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleService roleService, RoleRepository roleRepository,
			ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.roleService = roleService;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), user.getAuthorities());
	}


	@Override
	public User register(UserRegisterModel userRegisterModel) {
		
		User user = this.modelMapper.map(userRegisterModel, User.class);
		
		if(userRepository.count() == 0) {
			this.roleService.seedRolesInDb();
			user.setAuthorities(new HashSet<>(this.roleRepository.findAll().stream().collect(Collectors.toSet())));
		} else {
			user.setAuthorities(new HashSet<>(Set.of(this.roleRepository.findByAuthority("USER"))));
		}
		
		String encodedPassword = this.passwordEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPassword); 
		return this.userRepository.save(user);
	}


}







