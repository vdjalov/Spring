package app;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import app.data.models.User;
import app.data.repositories.UserRepository;
import app.error.userError.UserException;
import app.service.HashingService;
import app.service.UserService;
import app.service.implementations.HashingServiceImpl;
import app.service.implementations.UserServiceImpl;
import app.service.models.LoginUserServiceModel;
import app.service.models.RegisterUserServiceModel;


@SpringBootTest
class UserServiceTest {
	
	@Mock UserRepository userRepository;
	
	ModelMapper modelMapper;
	HashingService hashingService;
	UserService userService;

	@BeforeEach
	public void init() {
		modelMapper = new ModelMapper();
		hashingService = new HashingServiceImpl();
		userService = new UserServiceImpl(userRepository, modelMapper, hashingService);
		
	}
	
	
	@Test
	void testSaveUserNoHero() {
		RegisterUserServiceModel registerUserServiceModel = new RegisterUserServiceModel();
		registerUserServiceModel.setPassword("123");
		registerUserServiceModel.setConfirmPassword("123");
		registerUserServiceModel.setEmail("van@dam.com");
		registerUserServiceModel.setUsername("Vlad");
		
		User user = modelMapper.map(registerUserServiceModel, User.class);
		Mockito.when(userRepository.save(user))
			   .thenReturn(user);
		
		
		assertThat(user.getUsername()).isEqualTo(userRepository.save(user).getUsername());
		
	}
	
	
	@Test()
	void testSaveUserNoHeroWrongPassword() {
		RegisterUserServiceModel registerUserServiceModel = new RegisterUserServiceModel();
		registerUserServiceModel.setPassword("123");
		registerUserServiceModel.setConfirmPassword("13");
		registerUserServiceModel.setEmail("van@dam.com");
		registerUserServiceModel.setUsername("Vlad");
		
		boolean thrown = false;
		try {
			this.userService.save(registerUserServiceModel);
		} catch (UserException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}

	
	@Test()
	void testSaveUserNoHeroDuplicateUsername() {
		RegisterUserServiceModel registerUserServiceModel = new RegisterUserServiceModel();
		registerUserServiceModel.setPassword("123");
		registerUserServiceModel.setConfirmPassword("13");
		registerUserServiceModel.setEmail("van@dam.com");
		registerUserServiceModel.setUsername("Vlad");
		Optional<User> userNotPresent = null;
		Mockito.when(userRepository.findByUsername(Mockito.anyString())) 
				.thenReturn(userNotPresent);
		
		boolean thrown = false;
		try {
			this.userService.save(registerUserServiceModel);
		} catch (UserException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
		
	}
	
	
	@Test()
	void testLoginUserNoHeroDuplicateUsername() {
		String username = "vlad";
		String password = "123";
		LoginUserServiceModel loginUserServiceModel = new LoginUserServiceModel();
		loginUserServiceModel.setUsername(username);
		loginUserServiceModel.setPassword(password);
		Optional<User> user = null;
		Mockito.when(userRepository.findByUsernameAndPassword(Mockito.anyString(),Mockito.anyString())) 
				.thenReturn(user);
		
		boolean thrown = false;
		
				try {
					this.userService.validateLogin(loginUserServiceModel);
				} catch (Exception e) {
					thrown = true;
				}
		assertTrue(thrown);
	}
	
	
	@Test()
	void testLoginUserNoHeroSuccessfulLoginUsername() {
		String username = "vlad";
		String password = "123";
		LoginUserServiceModel loginUserServiceModel = new LoginUserServiceModel();
		loginUserServiceModel.setUsername(username);
		loginUserServiceModel.setPassword(password);
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
															
		Mockito.when(userRepository.findByUsernameAndPassword(Mockito.anyString(), Mockito.anyString())) 
						   .thenReturn(Optional.of(user));
		
		boolean thrown = true;
		
			try {				
				userService.validateLogin(loginUserServiceModel);
			} catch (Exception e) {
				thrown = false;
			}
			
		assertTrue(thrown);
	}
	
}






