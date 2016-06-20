package com.kss.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.kss.dao.api.UserDao;
import com.kss.domain.User;
import com.kss.utils.Constants;

@Repository("UserDao")
public class UserDaoImpl implements UserDao{
	
	@PersistenceContext
	private EntityManager em;

	public User getAdmin(String userId, String password) {
		TypedQuery<User> typedQuery = em.createQuery("select u from User u where u.id = '" + userId + "' and u.password = '" + password + "' and u.role = '" + Constants.ADMIN_ROLE + "' and rownum = 1" , User.class);
		List<User> users = typedQuery.getResultList(); 
	    return users.size() == 0 ? null : users.get(0);
	}

	public User getUser(String userId) {
		TypedQuery<User> typedQuery = em.createQuery("select u from User u where u.id = '" + userId + "' and rownum = 1" , User.class);
		List<User> users = typedQuery.getResultList(); 
	    return users.size() == 0 ? null : users.get(0);
	}
	
	public User saveUser(User user) {
		return em.merge(user);
	}
}
