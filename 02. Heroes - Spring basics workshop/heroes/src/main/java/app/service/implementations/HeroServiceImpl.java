package app.service.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import app.data.models.Hero;
import app.data.models.User;
import app.data.repositories.HeroRepository;
import app.data.repositories.UserRepository;
import app.service.HeroService;
import app.service.models.ValidateCreateHeroModel;
import app.service.models.ValidateLoginServiceModel;
import app.service.session.SessionService;
import app.web.models.HeroFightViewModel;
import app.web.models.HeroViewModel;

@Service
public class HeroServiceImpl implements HeroService {

	private HeroRepository heroRepository;
	private UserRepository userRepository;
	private ModelMapper modelMapper;
	private SessionService sessionService;
	
	@Autowired
	public HeroServiceImpl(HeroRepository heroRepository, UserRepository userRepository, ModelMapper modelMapper, SessionService sessionService) {
		this.heroRepository = heroRepository;
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.sessionService = sessionService;
	}

	@Override
	public ValidateCreateHeroModel save(ValidateCreateHeroModel validateCreateHeroModel) {
		Hero hero = this.modelMapper.map(validateCreateHeroModel, Hero.class);
		String username = ((ValidateLoginServiceModel) this.sessionService.getSessionAttribute("user")).getUsername();
		User user = this.userRepository.findByUsername(username).get();
		hero.setUser(user);
		this.heroRepository.save(hero);
		
		return validateCreateHeroModel;
	}

	@Override
	public HeroViewModel findHeroByName(String heroName) {
		return this.modelMapper.map(this.heroRepository.findByName(heroName).get(), HeroViewModel.class);
	}

	@Override
	public List<HeroViewModel> getAllHeroes() {
		String heroName = ((ValidateCreateHeroModel) this.sessionService.getSessionAttribute("hero")).getName();
		return this.heroRepository.findAll().stream()
					.filter(hero-> !hero.getName().equals(heroName))
					.map(hero -> this.modelMapper.map(hero, HeroViewModel.class))
					.collect(Collectors.toList());
	}

	@Override
	public HeroFightViewModel fightHeroes(String enemyHeroName) {
		String myHeroName =  ((ValidateCreateHeroModel) this.sessionService.getSessionAttribute("hero")).getName();
		Hero myHero = this.heroRepository.findByName(myHeroName).get();
		Hero enemyHero = this.heroRepository.findByName(enemyHeroName).get();
		
		return claculateWinnerAndReturnFightDeatils(myHero, enemyHero);
	}

	private HeroFightViewModel claculateWinnerAndReturnFightDeatils(Hero myHero, Hero enemyHero) {
	
		int winner = (myHero.getAttack() + (myHero.getStrength() * 4)) - (enemyHero.getDefence() + (enemyHero.getStamina() * 2));
		HeroFightViewModel heroFightViewModel = new HeroFightViewModel();
			if(winner >= 0) {
				myHero.setLevel(myHero.getLevel() + 1);
				myHero.setStamina(myHero.getStamina() + 5);
				myHero.setStrength(myHero.getStrength() + 5);
				this.heroRepository.save(myHero);
				heroFightViewModel.setWinnerName(myHero.getName());
			} else {
				enemyHero.setLevel(enemyHero.getLevel() + 1);
				enemyHero.setStamina(enemyHero.getStamina() + 5);
				enemyHero.setStrength(enemyHero.getStrength() + 5);
				this.heroRepository.save(enemyHero);
				heroFightViewModel.setWinnerName(enemyHero.getName());
			}
		heroFightViewModel.setEnemyHeroGender(enemyHero.getGender());
		heroFightViewModel.setEnemyHeroName(enemyHero.getName());
		heroFightViewModel.setUserHeroGender(myHero.getGender());
		heroFightViewModel.setUserHeroName(myHero.getName());	
		return heroFightViewModel;
	}

	
	
}
