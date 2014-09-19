package com.dsideal.shm.service;

import java.util.List;

import com.dsideal.shm.domain.Role;

/**
 * 
 * @author feilm220
 *
 */
public interface RoleService {

	Role add(Role role) throws Exception;
	Role edit(Role role) throws Exception;
	void delete(List<Role> roleList) throws Exception;
	
	Role getById(Long id);
}
