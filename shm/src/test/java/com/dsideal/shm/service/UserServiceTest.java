package com.dsideal.shm.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dsideal.shm.Application;
import com.dsideal.shm.domain.User;
import com.dsideal.shm.repository.UserRepository;

/**
 * 
 * @author feilm220
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class UserServiceTest {

	@Autowired
	private UserRepository userRepository;
	private UserService userService;
	
	@Before
	public void setUp() throws Exception{
		userService = new UserServiceImpl(userRepository);
	}
	
	@Test
	public void saveUser() throws Exception{
		User user = new User("feilm", "123456", "费利明", "275400898@qq.com", 0);
		userService.add(user);
		List<User> userList = userService.getAll();
		for(User u : userList){
			System.out.println(u.toString());
		}
		
		User user2 = userRepository.findByLoginName("admin");
		System.out.println(user2.getLoginName());
		
		User user3 = userService.getByLoginNameAndPassword("admin", "123456");
		System.out.println(user3.getLoginName() + user3.getRealName());
	}
}
