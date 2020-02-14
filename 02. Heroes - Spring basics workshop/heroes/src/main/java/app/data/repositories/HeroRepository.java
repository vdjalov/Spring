package app.data.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.data.models.Hero;
import app.data.models.User;

public interface HeroRepository extends JpaRepository<Hero, Integer>{

	Optional<Hero> findByName(String heroName);

}
