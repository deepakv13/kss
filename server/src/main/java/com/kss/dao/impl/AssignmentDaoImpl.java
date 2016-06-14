package com.kss.dao.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.kss.domain.Assignment;


@Repository("AssignmentDao")
public class AssignmentDaoImpl  {

	@PersistenceContext
	private EntityManager em;

	
	public void createAssignment(Assignment assignment) {
		em.persist(assignment);
	}

	
	public Collection<Assignment> getAllAssignment() {
		TypedQuery<Assignment> typedQuery = em.createQuery("select w from Assignment w", Assignment.class);
	    return typedQuery.getResultList();
	}
	
	
	public Assignment getAssignment(int id ){
		TypedQuery<Assignment> typedQuery = em.createQuery("select a from Assignment a where a.id = '" + id + "'", Assignment.class);
		List<Assignment> assignments = typedQuery.getResultList();
		if(assignments.isEmpty()){
			return null;
		}
	    return assignments.get(0);
	}	
	
	
	public Assignment updateAssignment(Assignment assignment) {
		return em.merge(assignment);
	}
	
	public void deleteAssignment(int assignmentId) {
		Assignment assignment = getAssignment(assignmentId);
		em.remove(assignment);
	}
}
