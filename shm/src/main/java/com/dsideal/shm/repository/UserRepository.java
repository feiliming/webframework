package com.dsideal.shm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dsideal.shm.domain.User;

/**
 * 
 * @author feilm220
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByLoginNameAndPassword(String loginName, String password);
	
	User findByLoginName(String loginName);
	
	Page<User> findByLoginNameContaining(String loginName, Pageable pageable);
	
	Page<User> findByRealNameContaining(String realName, Pageable pageable);
}
