package com.kss.service.api;

import com.kss.domain.UserAssignment;
import com.kss.web.dto.UserAssignmentCollectionDTO;

public interface UserAssignmentService {
	
	public UserAssignmentCollectionDTO getUserAssignments(String userId);
	public UserAssignment getOrTakeUserAssignment(String userId, Integer assignmentId);
	public Object saveUserAssignmentItems(UserAssignment userAssignment);
}
