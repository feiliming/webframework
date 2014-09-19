package com.dsideal.shm.service;

import java.util.List;

import com.dsideal.shm.domain.Organization;

/**
 * 
 * @author feilm220
 *
 */
public interface OrganizatioinService {

	Organization add(Organization organization) throws Exception;
	Organization edit(Organization organization) throws Exception;
	void delete(List<Organization> organizationList) throws Exception;
	
	Organization getById(Long id);
}
