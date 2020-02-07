package app.data.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.data.models.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{

	Optional<Item> findByName(String itemName);
}
