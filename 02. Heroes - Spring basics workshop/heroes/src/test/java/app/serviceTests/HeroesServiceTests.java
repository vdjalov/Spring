package app.serviceTests;


import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import app.base.BaseTest;
import app.data.models.Hero;
import app.data.repositories.HeroRepository;
import app.data.repositories.UserRepository;
import app.service.HeroService;
import app.service.models.ValidateCreateHeroModel;
import app.service.session.SessionService;
import app.web.models.HeroFightViewModel;

@SpringBootTest
@ExtendWith(value = MockitoExtension.class)
class HeroesServiceTests extends BaseTest {

	@MockBean
	HeroRepository heroRepository;
	
	@MockBean
	UserRepository userRepository;
	
	@MockBean
	SessionService session;
	
	@Autowired
	HeroService heroService;
	
	private ValidateCreateHeroModel validateCreateHeroModel;
	
	@BeforeEach
	public void initSetup() {
		validateCreateHeroModel = new ValidateCreateHeroModel();
		validateCreateHeroModel.setName("one");
		when(session.getSessionAttribute("hero")).thenReturn(validateCreateHeroModel);
	}
	
	
	@Test
	void testFightHeroesTwoDifferentHeroesShouldReturnHeroViewModelEnemyHeroWins() {
	
		Hero myHero = new Hero();
		myHero.setName("one");
		myHero.setAttack(1);
		myHero.setDefence(1);
		myHero.setStamina(1);
		myHero.setStrength(1);
		myHero.setLevel(0);
		Hero enemyHero = new Hero();
		enemyHero.setName("two");
		enemyHero.setAttack(2);
		enemyHero.setDefence(2);
		enemyHero.setStamina(2);
		enemyHero.setStrength(2);
		enemyHero.setLevel(0);
		
		when(heroRepository.findByName("one")).thenReturn(Optional.of(myHero));
		when(heroRepository.findByName("two")).thenReturn(Optional.of(enemyHero));
		
		HeroFightViewModel heroFightViewModel = this.heroService.fightHeroes("two");
		
		assertTrue(heroFightViewModel.getWinnerName().equals(enemyHero.getName()));
		assertTrue(enemyHero.getLevel() == 1);
		assertTrue(enemyHero.getStamina() == 7);
		assertTrue(enemyHero.getStrength() == 7);
	}
	
	
	@Test
	void testFightHeroesTwoDifferentHeroesShouldReturnHeroViewModelUserHeroWins() {
		
		Hero myHero = new Hero();
		myHero.setName("one");
		myHero.setAttack(1);
		myHero.setDefence(1);
		myHero.setStamina(1);
		myHero.setStrength(1);
		myHero.setLevel(0);
		Hero enemyHero = new Hero();
		enemyHero.setName("two");
		enemyHero.setAttack(0);
		enemyHero.setDefence(0);
		enemyHero.setStamina(0);
		enemyHero.setStrength(0);
		enemyHero.setLevel(0);
		
		when(heroRepository.findByName("one")).thenReturn(Optional.of(myHero));
		when(heroRepository.findByName("two")).thenReturn(Optional.of(enemyHero));
		
		HeroFightViewModel heroFightViewModel = this.heroService.fightHeroes("two");
		
		assertTrue(heroFightViewModel.getWinnerName().equals(myHero.getName()));
		assertTrue(myHero.getLevel() == 1);
		assertTrue(myHero.getStamina() == 6);
		assertTrue(myHero.getStrength() == 6);
		
	}

}
