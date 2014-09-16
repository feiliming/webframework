package com.dsideal.shm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dsideal.shm.domain.User;

/**
 * 
 * @author feilm220
 *
 */
public interface UserService {

    User add(User user) throws Exception;
    User edit(User user) throws Exception;
    void delete(List<User> userList) throws Exception;
    
    User getByLoginNameAndPassword(String loginName, String password);

    User getById(Long id);
    User getByLoginName(String loginName);
    Page<User> getByLoginNameContaining(String loginName, Pageable pageable);
    Page<User> getByRealNameContaining(String realName, Pageable pageable);
    
    List<User> getAll();
    Page<User> getByPage(Pageable pageable);
}
