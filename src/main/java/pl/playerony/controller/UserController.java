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
import pl.playerony.model.impl.User;
import pl.playerony.service.UserService;

@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/save")
	public @ResponseBody Map<String, Object> saveUser(@RequestBody User user) throws DatabaseException, InputException {
		Map<String, Object> map = new HashMap<>();
		
		userService.saveUser(user);
		map.put("status", "success");
		
		return map;
	}
	
	@GetMapping("/all")
	public @ResponseBody Map<String, Object> selectAll() throws DatabaseException {
		Map<String, Object> map = new HashMap<>();
		
		map.put("users", userService.selectUsers());
		map.put("status", "success");
		
		return map;
	}
	
	@GetMapping("/get/{id}")
	public @ResponseBody Map<String, Object> selectUser(@PathVariable("id") Long id) throws DatabaseException {
		Map<String, Object> map = new HashMap<>();
		
		map.put("user", userService.selectUser(id));
		map.put("status", "success");
		
		return map;
	}
	
	@DeleteMapping("/delete/{id}")
	public @ResponseBody Map<String, Object> removeUser(@PathVariable("id") Long id) throws DatabaseException
	{
		Map<String, Object> map = new HashMap<>();
		
		userService.removeUser(id);
		map.put("status", "success");
		
		return map;
	}
	
}
