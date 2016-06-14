package com.kss.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kss.dao.api.UserDao;
import com.kss.domain.User;
import com.kss.service.api.LoginService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserDao userDao;
	
	public User getUser(String soeId, String password) {
		User user = null;
		soeId = soeId.toLowerCase();
		user = userDao.getUser(soeId);
		if (user.getRole().equals(User.Role.ADMIN)) {
			if (!user.getPassword().equals(password)) {
				return null;
			}
		}
		return user;
	}
}
