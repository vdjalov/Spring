package app.service;

import app.service.models.CreateHeroServiceModel;
import app.service.models.HeroDetailsServiceModel;

public interface HeroService {

	public void save(CreateHeroServiceModel createHeroServiceModel);

	public HeroDetailsServiceModel findHeroByName(String name);
}
