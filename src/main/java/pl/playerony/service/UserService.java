package pl.playerony.service;

import java.util.List;

import pl.playerony.exception.DatabaseException;
import pl.playerony.exception.InputException;
import pl.playerony.model.impl.User;

public interface UserService {
	Boolean saveUser(User user) throws DatabaseException, InputException;
	
	User selectUser(Long id) throws DatabaseException;
	
	List<User> selectUsers() throws DatabaseException;
	
	Boolean removeUser(Long id) throws DatabaseException;
}
