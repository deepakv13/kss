package com.kss.service.api;

import com.kss.domain.User;

public interface LoginService {

	public User getUser(String soeId, String password);
}

