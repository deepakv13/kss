package com.kss.dao.api;

import java.util.Collection;

import com.kss.domain.UserAssignment;

public interface UserAssignmentDao {
	public Collection<UserAssignment> getAllAttendedAssignments(String user);
	public void createUserAssignment(UserAssignment userAssignment);
	public Collection<UserAssignment> getUserAssignment(String userId, Integer assignmentId);
	public UserAssignment saveUserAssignment(UserAssignment userAssignment);
}
