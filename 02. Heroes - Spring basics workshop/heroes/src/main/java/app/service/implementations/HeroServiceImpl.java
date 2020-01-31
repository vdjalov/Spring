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
import app.service.models.HeroDetailsServiceModel;

@Service
public class HeroServiceImpl implements HeroService{

	private HeroRepository heroRepository;
	private UserRepository userRepository;
	private ModelMapper modelMapper;
	private HttpSession session;
	
	@Autowired
	public HeroServiceImpl(HeroRepository heroRepository, UserRepository userRepository, ModelMapper modelMapper, HttpSession session) {
		this.heroRepository = heroRepository;
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.session = session;
	}


	@Override
	public void save(CreateHeroServiceModel createHeroServiceModel) {
		String username = this.session.getAttribute("username").toString();
		createHeroServiceModel.setUser(this.userRepository.findByUsername(username).get());
		Hero hero = this.modelMapper.map(createHeroServiceModel, Hero.class);
		
		this.heroRepository.saveAndFlush(hero);
		this.session.setAttribute("hero", hero.getName());
		User user = this.userRepository.findByUsername(username).get();
		user.setHero(hero);
		this.userRepository.saveAndFlush(user);
	}


	@Override
	public HeroDetailsServiceModel findHeroByName(String name) {
		return this.modelMapper.map(this.heroRepository.findByName(name).get(), HeroDetailsServiceModel.class);
		
	}

}
