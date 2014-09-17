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
	
	Page<User> findByLoginNameContainingOrderByIdAsc(String loginName, Pageable pageable);
	
	Page<User> findByRealNameContainingOrderByIdAsc(String realName, Pageable pageable);
}
