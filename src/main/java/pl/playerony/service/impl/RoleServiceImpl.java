package pl.playerony.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.playerony.exception.DatabaseException;
import pl.playerony.exception.InputException;
import pl.playerony.model.impl.Role;
import pl.playerony.repository.RoleRepository;
import pl.playerony.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	private RoleRepository roleRepository;
	
	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository){
		this.roleRepository = roleRepository;
	}
	
	@Override
	public Boolean saveRole(Role role) throws DatabaseException, InputException {
		if(role.getId() != null)
			return roleRepository.updateRole(role.getId(), role);
		else
			return roleRepository.insertRole(role);
	}

	@Override
	public Role selectRole(Long id) throws DatabaseException {
		return roleRepository.selectRoleById(id);
	}

	@Override
	public List<Role> selectRoles() throws DatabaseException {
		return roleRepository.selectRoles();
	}

	@Override
	public Boolean removeRole(Long id) throws DatabaseException {
		return roleRepository.removeRole(id);
	}

}
