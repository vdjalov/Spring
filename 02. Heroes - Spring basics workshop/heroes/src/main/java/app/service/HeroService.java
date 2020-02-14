package app.service;

import app.service.models.ValidateCreateHeroModel;
import app.web.models.HeroViewModel;

public interface HeroService {

	public ValidateCreateHeroModel save(ValidateCreateHeroModel validateCreateHeroModel);

	public HeroViewModel findHeroByName(String heroName);

	
}
