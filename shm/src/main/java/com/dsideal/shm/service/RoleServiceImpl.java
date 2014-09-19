package com.dsideal.shm.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsideal.shm.domain.Role;
import com.dsideal.shm.repository.RoleRepository;

/**
 * 
 * @author feilm220
 *
 */
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	private final RoleRepository repository;
	
	@Inject
	public RoleServiceImpl(RoleRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Role add(Role role) throws Exception {
		LOGGER.info("create {}", role);
		return repository.save(role);
	}

	@Override
	@Transactional
	public Role edit(Role role) throws Exception {
		LOGGER.info("update {}", role);
		return repository.save(role);
	}

	@Override
	@Transactional
	public void delete(List<Role> roleList) throws Exception {
		repository.delete(roleList);
		LOGGER.info("delete {}", roleList);
	}

	@Override
	public Role getById(Long id) {
		return repository.findOne(id);
	}

}
