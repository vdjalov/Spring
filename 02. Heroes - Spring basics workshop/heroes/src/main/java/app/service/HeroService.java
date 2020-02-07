package app.service;

import app.service.models.CreateHeroServiceModel;
import app.web.models.HeroViewModel;

public interface HeroService {

	void save(CreateHeroServiceModel createHeroServiceModel);
	HeroViewModel getHero(String username);

	
}
