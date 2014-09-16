package com.dsideal.shm.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsideal.shm.domain.User;
import com.dsideal.shm.repository.UserRepository;
import com.dsideal.shm.util.MD5Util;

/**
 * 
 * @author feilm220
 * 还没加排序.
 *
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private final UserRepository repository;
	
	@Inject
	public UserServiceImpl(final UserRepository repository){
		this.repository = repository;
	}

	@Override
	@Transactional
	public synchronized User add(User user) throws Exception {
		User existing = repository.findByLoginName(user.getLoginName());
		if(existing != null){
			LOGGER.error("用户登录名已经存在.");
			throw new Exception("用户登录名已经存在.");
		}
		LOGGER.info("create {}", user);
		return repository.save(user);
	}

	@Override
	@Transactional
	public User edit(User user) throws Exception {
		User existing = repository.findByLoginName(user.getLoginName());
		if(existing != null){
			LOGGER.error("用户登录名已经存在.");
			throw new Exception("用户登录名已经存在.");
		}
		LOGGER.info("update {}", user);
		return repository.save(user);
	}

	@Override
	@Transactional
	public void delete(List<User> userList) throws Exception {
		repository.delete(userList);
		LOGGER.info("delete {}", userList);
	}

	@Override
	public User getByLoginNameAndPassword(String loginName, String password) {
		return repository.findByLoginNameAndPassword(loginName, MD5Util.md5(password));
	}

	@Override
	public User getById(Long id) {
		return repository.findOne(id);
	}

	@Override
	public User getByLoginName(String loginName) {
		return repository.findByLoginName(loginName);
	}

	@Override
	public Page<User> getByLoginNameContaining(String loginName,
			Pageable pageable) {
		return repository.findByLoginNameContaining(loginName, pageable);
	}

	@Override
	public Page<User> getByRealNameContaining(String realName, Pageable pageable) {
		return repository.findByRealNameContaining(realName, pageable);
	}

	@Override
	public List<User> getAll(){
		return repository.findAll();
	}
	
	@Override
	public Page<User> getByPage(Pageable pageable) {
		return repository.findAll(pageable);
	}

}
