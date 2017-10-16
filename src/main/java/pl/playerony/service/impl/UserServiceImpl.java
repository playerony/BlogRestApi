package pl.playerony.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.playerony.exception.DatabaseException;
import pl.playerony.exception.InputException;
import pl.playerony.model.impl.User;
import pl.playerony.repository.UserRepository;
import pl.playerony.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public Boolean saveUser(User user) throws DatabaseException, InputException {
		if(user.getId() != null)
			return userRepository.updateUser(user.getId(), user);
		else
			return userRepository.insertUser(user);
	}

	@Override
	public User selectUser(Long id) throws DatabaseException {
		return userRepository.selectUserById(id);
	}

	@Override
	public List<User> selectUsers() throws DatabaseException {
		return userRepository.selectUsers();
	}

	@Override
	public Boolean removeUser(Long id) throws DatabaseException {
		return userRepository.removeUser(id);
	}

}
