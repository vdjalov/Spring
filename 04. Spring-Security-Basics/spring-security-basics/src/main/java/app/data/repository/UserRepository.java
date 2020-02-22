package app.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.data.models.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);
	
}
