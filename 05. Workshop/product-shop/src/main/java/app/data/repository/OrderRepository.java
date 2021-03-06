package app.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.data.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
