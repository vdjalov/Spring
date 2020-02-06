package app.service.implementations;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.data.models.Hero;
import app.data.models.User;
import app.data.repositories.HeroRepository;
import app.data.repositories.UserRepository;
import app.service.HeroService;
import app.service.models.CreateHeroServiceModel;
import app.web.models.HeroViewModel;


@Service
public class HeroServiceImpl implements HeroService {

	private UserRepository userRepository;
	private HeroRepository heroRepository;
	private ModelMapper modelMapper;
	private HttpSession session;
	
	@Autowired
	public HeroServiceImpl(UserRepository userRepository, HeroRepository heroRepository, ModelMapper modelMapper, HttpSession session) {
		this.userRepository = userRepository;
		this.heroRepository = heroRepository;
		this.modelMapper = modelMapper;
		this.session = session;
	}

	
	@Override
	public void save(CreateHeroServiceModel createHeroServiceModel) {
		Hero hero = this.modelMapper.map(createHeroServiceModel, Hero.class);  // Optional hero to be checked if unique agains all heroes
		User user = this.userRepository.findByUsername(this.session.getAttribute("username").toString()).get();
		hero.setUser(user);
		this.heroRepository.save(hero);
	}

	@Override
	public HeroViewModel getHero(String name) {
		Hero hero = this.heroRepository.findByName(name).get();
		HeroViewModel heroViewModel = this.modelMapper.map(hero, HeroViewModel.class);
		return heroViewModel;
	}

}
