package app.service;

import java.util.List;

import app.service.models.ValidateCreateHeroModel;
import app.web.models.HeroFightViewModel;
import app.web.models.HeroViewModel;

public interface HeroService {

	public ValidateCreateHeroModel save(ValidateCreateHeroModel validateCreateHeroModel);

	public HeroViewModel findHeroByName(String heroName);

	public List<HeroViewModel> getAllHeroes();

	public HeroFightViewModel fightHeroes(String enemyHeroName);

	
}
