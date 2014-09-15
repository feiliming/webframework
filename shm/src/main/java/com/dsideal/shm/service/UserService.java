package com.dsideal.shm.service;

import java.util.List;

import com.dsideal.shm.domain.User;

public interface UserService {

    User save(User user);

    List<User> getUserList();
}
