package com.dsideal.shm.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dsideal.shm.domain.User;
import com.dsideal.shm.repository.UserRepository;

/**
 * 
 * @author feilm220
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private final UserRepository repository;
	
	@Inject
	public UserServiceImpl(final UserRepository repository){
		this.repository = repository;
	}

	@Override
	@Transactional
	public User save(User user) {
		LOGGER.debug("Create {}", user);
		User existing = repository.findOne(user.getId());
		if(existing != null){
			
		}
		return repository.save(user);
	}

	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return null;
	}

}
