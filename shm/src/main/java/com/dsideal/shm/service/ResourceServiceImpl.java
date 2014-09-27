package com.dsideal.shm.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsideal.shm.domain.Resource;
import com.dsideal.shm.domain.User;
import com.dsideal.shm.repository.ResourceRepository;
import com.dsideal.shm.repository.UserRepository;
import com.dsideal.shm.vo.Tree;

/**
 * 
 * @author feilm220
 *
 */
@Service
@Transactional(readOnly = true)
public class ResourceServiceImpl implements ResourceService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceServiceImpl.class);
	
	private final ResourceRepository resourceRepository;
	private final UserRepository userReposity;
	
	@Inject
	public ResourceServiceImpl(ResourceRepository resourceRepository, final UserRepository userReposity) {
		this.resourceRepository = resourceRepository;
		this.userReposity = userReposity;
	}

	@Override
	@Transactional
	public Resource add(Resource resource) throws Exception {
		LOGGER.info("create {}", resource);
		return resourceRepository.save(resource);
	}

	@Override
	@Transactional
	public Resource edit(Resource resource) throws Exception {
		LOGGER.info("update {}", resource);
		return resourceRepository.save(resource);
	}

	@Override
	@Transactional
	public void delete(List<Resource> resourceList) throws Exception {
		resourceRepository.delete(resourceList);
		LOGGER.info("delete {}", resourceList);
	}

	@Override
	public Resource getById(Long id) {
		return resourceRepository.findOne(id);
	}

	@Override
	public List<Tree> getByUserId(Long userId) {
		User user = userReposity.findOne(userId);
		resourceRepository.findAll(new Sort(Sort.Direction.ASC, "sequence"));
		
		return null;
	}

}
