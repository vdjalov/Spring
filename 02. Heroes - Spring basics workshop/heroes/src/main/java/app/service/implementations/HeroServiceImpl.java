package app.service.implementations;

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

	
	
}
