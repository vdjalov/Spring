package app.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.data.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
}
