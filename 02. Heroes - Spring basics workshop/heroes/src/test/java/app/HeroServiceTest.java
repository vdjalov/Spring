package app;


import static org.junit.Assert.assertTrue;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import app.data.models.User;
import app.data.repositories.HeroRepository;
import app.data.repositories.UserRepository;
import app.service.HeroService;
import app.service.implementations.HeroServiceImpl;
import app.service.models.CreateHeroServiceModel;

@SpringBootTest
public class HeroServiceTest {

	@Mock UserRepository userRepository;
	@Mock HeroRepository heroRepository;
	ModelMapper modelMapper;
	HeroService heroService;

	@Mock HttpSession session;
	
	
	@BeforeEach
	void init() {
		modelMapper = new ModelMapper();
		heroService = new HeroServiceImpl(userRepository, heroRepository, modelMapper, session);
	}
	
	@Test
	void testHeroSaveShouldThrowException() {
		Mockito.when(heroRepository.findByName(Mockito.anyString())).thenReturn(Mockito.any());
		
		boolean thrown = false;
		try {
			this.heroService.save(new CreateHeroServiceModel());
		} catch (Exception e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}
	
	@Test
	void testHeroSaveShouldReturnSavedHeroShouldNotThrowException() {
		User user = new User();
		user.setUsername("vlad");
		user.setId(1);
		Mockito.when(session.getAttribute(Mockito.anyString())).thenReturn("vlad");
		Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
		
		boolean thrown = true;
		try {
			this.heroService.save(new CreateHeroServiceModel());
		} catch (Exception e) {
			thrown = false;
		}
		
		assertTrue(thrown);
	}
	
	
}







