package com.dsideal.shm.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsideal.shm.domain.Organization;
import com.dsideal.shm.repository.OrganizationRepository;

/**
 * 
 * @author feilm220
 *
 */
@Service
@Transactional(readOnly = true)
public class OrganizationServiceImpl implements OrganizatioinService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationServiceImpl.class);
	
	private final OrganizationRepository repository;
	
	@Inject
	public OrganizationServiceImpl(OrganizationRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Organization add(Organization organization) throws Exception {
		LOGGER.info("create {}", organization);
		return repository.save(organization);
	}

	@Override
	@Transactional
	public Organization edit(Organization organization) throws Exception {
		LOGGER.info("update {}", organization);
		return repository.save(organization);
	}

	@Override
	@Transactional
	public void delete(List<Organization> organizationList) throws Exception {
		repository.delete(organizationList);
		LOGGER.info("delete {}", organizationList);
	}

	@Override
	public Organization getById(Long id) {
		return repository.findOne(id);
	}

}
