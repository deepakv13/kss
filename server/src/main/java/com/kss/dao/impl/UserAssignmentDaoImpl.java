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
		TypedQuery<UserAssignment> typedQuery = em.createQuery("select ua from UserAssignment ua where ua.user like '" + userId + "'", UserAssignment.class);
		return typedQuery.getResultList();
	}
	
	public void createUserAssignment(UserAssignment userAssignment) {
		em.merge(userAssignment);
	}
	
	public Collection<UserAssignment> getUserAssignment(String userId, Integer assignmentId) {
		TypedQuery<UserAssignment> typedQuery = em.createQuery("select ua from UserAssignment ua where ua.user = '" + userId + "' and ua.assignment = '" + assignmentId + "'", UserAssignment.class);
		return typedQuery.getResultList();
	}

	public UserAssignment saveUserAssignment(UserAssignment userAssignment) {
		return em.merge(userAssignment);
	}
	
}
