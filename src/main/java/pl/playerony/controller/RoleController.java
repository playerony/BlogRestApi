package pl.playerony.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.playerony.exception.DatabaseException;
import pl.playerony.exception.InputException;
import pl.playerony.model.impl.Role;
import pl.playerony.service.RoleService;

@Controller
@CrossOrigin
@RequestMapping("/role")
public class RoleController {
	private RoleService roleService;
	
	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@PostMapping("/save")
	public @ResponseBody Map<String, Object> saveRole(@RequestBody Role role) throws DatabaseException, InputException
	{
		Map<String, Object> map = new HashMap<>();
		
		roleService.saveRole(role);
		map.put("status", "success");
		
		return map;
	}
	
	@GetMapping("/all")
	public @ResponseBody Map<String, Object> selectAll() throws DatabaseException {
		Map<String, Object> map = new HashMap<>();
		
		map.put("roles", roleService.selectRoles());
		map.put("status", "success");
		
		return map;
	}
	
	@GetMapping("/get/{id}")
	public @ResponseBody Map<String, Object> selectRole(@PathVariable("id") Long id) throws DatabaseException {
		Map<String, Object> map = new HashMap<>();
		
		map.put("role", roleService.selectRole(id));
		map.put("status", "success");
		
		return map;
	}
	
	@DeleteMapping("/delete/{id}")
	public @ResponseBody Map<String, Object> remove(@PathVariable("id") Long id) throws DatabaseException
	{
		Map<String, Object> map = new HashMap<>();
		
		roleService.removeRole(id);
		map.put("status", "success");
		
		return map;
	}
	
}
