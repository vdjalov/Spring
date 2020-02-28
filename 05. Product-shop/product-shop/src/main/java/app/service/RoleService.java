package app.service;

import java.util.Set;

import app.data.models.Role;

public interface RoleService {
	void seedDb();

	int getRepositorySize();

	Role findByAuthority(String string);

	Set<Role> findAllAuthorities();
}
