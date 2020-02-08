package app.service;

import app.service.models.CreateHeroServiceModel;
import app.web.models.HeroViewModel;

public interface HeroService {

	void save(CreateHeroServiceModel createHeroServiceModel) throws Exception;
	HeroViewModel getHero(String username);

	
}
