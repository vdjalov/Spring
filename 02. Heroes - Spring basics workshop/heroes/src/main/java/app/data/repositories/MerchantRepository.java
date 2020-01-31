package app.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.data.models.HeroItems;


@Repository
public interface MerchantRepository extends JpaRepository<HeroItems, Integer>{

}
