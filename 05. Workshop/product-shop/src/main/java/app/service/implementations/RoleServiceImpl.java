package app.service.implementations;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.data.models.Role;
import app.data.repository.RoleRepository;
import app.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepository;
	
	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	
	@Override
	public void seedDb() {
		List<Role> allRoles = 
				List.of(
					new Role("ADMIN"),	
					new Role("MODERATOR"),
					new Role("ROOT"),
					new Role("USER")
				);
		
		this.roleRepository.saveAll(allRoles);
	}


	@Override
	public int getRepositorySize() {
		return roleRepository.findAll().size();
	}


	@Override
	public Role findByAuthority(String authority) {
		return this.roleRepository.findByAuthority(authority);
	}


	@Override
	public Set<Role> findAllAuthorities() {
		Set<Role> allAuthorities = this.roleRepository.findAll().stream().collect(Collectors.toSet());
		return allAuthorities;
	}


}
