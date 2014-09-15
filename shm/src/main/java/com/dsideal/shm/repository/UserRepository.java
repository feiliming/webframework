package com.dsideal.shm.repository;

import org.springframework.data.repository.CrudRepository;

import com.dsideal.shm.domain.User;

/**
 * 
 * @author feilm220
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {

	
}
