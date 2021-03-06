package com.kss.dao.impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.kss.dao.api.UserAssignmentDao;
import com.kss.domain.UserAssignment;

@Repository("UserAssignmentDao")
public class UserAssignmentDaoImpl implements UserAssignmentDao{

	@PersistenceContext
	private EntityManager em;
	
	public Collection<UserAssignment> getAllAttendedAssignments(String userId) {
		TypedQuery<UserAssignment> typedQuery = em.createQuery("select ua from UserAssignment ua where lower(ua.user) = '" + userId.toLowerCase() + "'", UserAssignment.class);
		return typedQuery.getResultList();
	}
	
	public void createUserAssignment(UserAssignment userAssignment) {
		em.merge(userAssignment);
	}
	
	public UserAssignment getUserAssignment(String userId, Integer assignmentId) {
		TypedQuery<UserAssignment> typedQuery = em.createQuery("select ua from UserAssignment ua where lower(ua.user) = '" + userId.toLowerCase() + "' and ua.assignment = '" + assignmentId + "'", UserAssignment.class);
		if (typedQuery.getResultList().size() > 0) {
			return typedQuery.getResultList().get(0);
		}
		return null;
	}

	public UserAssignment saveUserAssignment(UserAssignment userAssignment) {
		return em.merge(userAssignment);
	}
	
}
