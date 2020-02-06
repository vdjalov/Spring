package app.data.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.data.models.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByUsernameAndPassword(String username, String password);

	Optional<User> findByUsername(String string);
	
}
