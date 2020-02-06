package app.data.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.data.models.Hero;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer>{

	Optional<Hero> findByName(String name);
}
