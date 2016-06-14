package com.kss.dao.api;

import com.kss.domain.User;

public interface UserDao {
	public User getAdmin(String userId, String password);
	public User getUser(String userId);
}
