package app.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.data.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	

}
