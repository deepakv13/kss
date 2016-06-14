package com.kss.service.api;

import java.util.Collection;

import com.kss.domain.Assignment;

public interface AssignmentService {
	
	public Collection<Assignment> getAssignment(int assignmentId) throws Exception;
	
	public Collection<Assignment> getAllAssignment() throws Exception;
	
	public Assignment saveAssignment(Assignment assignment) throws Exception;
	
	public void deleteAssignment(int  assignmentId);
	
	public Assignment publishAssignment(Assignment assignment) throws Exception;
}
