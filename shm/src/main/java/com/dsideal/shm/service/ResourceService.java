package com.dsideal.shm.service;

import java.util.List;

import com.dsideal.shm.domain.Resource;

/**
 * 
 * @author feilm220
 *
 */
public interface ResourceService {

	Resource add(Resource resource) throws Exception;
	Resource edit(Resource resource) throws Exception;
	void delete(List<Resource> resourceList) throws Exception;
	
	Resource getById(Long id);
}
