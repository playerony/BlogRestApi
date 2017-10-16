package pl.playerony.service;

import java.util.List;

import pl.playerony.exception.DatabaseException;
import pl.playerony.exception.InputException;
import pl.playerony.model.impl.Role;

public interface RoleService {
	Boolean saveRole(Role role) throws DatabaseException, InputException;
	
	Role selectRole(Long id) throws DatabaseException;
	
	List<Role> selectRoles() throws DatabaseException;
	
	Boolean removeRole(Long id) throws DatabaseException;
}
