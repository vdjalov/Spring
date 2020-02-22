package app.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.data.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByAuthority(String string);

}
