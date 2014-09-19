package com.dsideal.shm.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsideal.shm.domain.Resource;
import com.dsideal.shm.repository.ResourceRepository;

/**
 * 
 * @author feilm220
 *
 */
@Service
@Transactional(readOnly = true)
public class ResourceServiceImpl implements ResourceService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceServiceImpl.class);
	
	private final ResourceRepository repository;
	
	@Inject
	public ResourceServiceImpl(ResourceRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Resource add(Resource resource) throws Exception {
		LOGGER.info("create {}", resource);
		return repository.save(resource);
	}

	@Override
	@Transactional
	public Resource edit(Resource resource) throws Exception {
		LOGGER.info("update {}", resource);
		return repository.save(resource);
	}

	@Override
	@Transactional
	public void delete(List<Resource> resourceList) throws Exception {
		repository.delete(resourceList);
		LOGGER.info("delete {}", resourceList);
	}

	@Override
	public Resource getById(Long id) {
		return repository.findOne(id);
	}

}
