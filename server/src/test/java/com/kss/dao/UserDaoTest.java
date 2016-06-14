package com.kss.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kss.dao.api.UserDao;
import com.kss.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-config.xml" })
public class UserDaoTest {
	
	@Autowired
	UserDao userDao;
	
	@Test
	public void getUserInfoTest() {
		User user = userDao.getUserInfo("AM35181");
		System.out.println(user);
	}
}
