package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.data.models.Role;
import app.data.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepository;
	
	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public void seedRolesInDb() {
		roleRepository.saveAndFlush((new Role("ADMIN")));
		roleRepository.saveAndFlush((new Role("USER")));
	}
	
	
	
}
