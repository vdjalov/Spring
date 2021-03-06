package app.data.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.data.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Query(value = "select * from products p", nativeQuery = true)
	Page<Product> findAllProducts(Pageable pageable);
}
