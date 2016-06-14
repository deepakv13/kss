package com.kss.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kss.dao.api.UserAssignmentDao;
import com.kss.dao.impl.AssignmentDaoImpl;
import com.kss.domain.Assignment;
import com.kss.domain.AssignmentStatus;
import com.kss.domain.User;
import com.kss.domain.UserAssignment;
import com.kss.service.api.UserAssignmentService;
import com.kss.web.dto.AssignmentDTO;
import com.kss.web.dto.UserAssignmentCollectionDTO;

@Service
@Transactional
public class UserAssignmentServiceImpl implements UserAssignmentService{
	
	@Autowired
	AssignmentDaoImpl assignmentDao;
	
	@Autowired
	UserAssignmentDao userAssignmentDao;
	
	public UserAssignmentCollectionDTO getUserAssignments(String userId) {
			return getGroupedAssignments(userAssignmentDao.getAllAttendedAssignments(userId), assignmentDao.getAllAssignment()); 
	}
	
	public UserAssignment getOrTakeUserAssignment(String userId, Integer assignmentId) {
		List<UserAssignment> userAssignments = new ArrayList<UserAssignment>(userAssignmentDao.getUserAssignment(userId, assignmentId));		
		if (userAssignments.size() == 0) {
			return takeAssignment(userId, assignmentId);
		}
		else {
			return userAssignments.get(0);
		}
	}

	public UserAssignment saveUserAssignmentItems(UserAssignment userAssignment) {
		userAssignmentDao.saveUserAssignment(userAssignment);
		return userAssignment;
	}

	private UserAssignmentCollectionDTO getGroupedAssignments(Collection<UserAssignment> userAssignments, Collection<Assignment> allAssignments) {
		List<AssignmentDTO> attendedAssignments = new LinkedList<AssignmentDTO>();
		List<AssignmentDTO> availableAssignments = new LinkedList<AssignmentDTO>();
		
		for (UserAssignment userAssignment : userAssignments) {
			AssignmentDTO assignmentDTO = new AssignmentDTO(userAssignment);
			attendedAssignments.add(assignmentDTO);
		}
		
		for (Assignment assignment : allAssignments) {
			AssignmentDTO assignmentDTO = new AssignmentDTO(assignment);
			availableAssignments.add(assignmentDTO);
		}
		availableAssignments.removeAll(attendedAssignments);
		return new UserAssignmentCollectionDTO(attendedAssignments, availableAssignments);
	}

	private AssignmentDTO removeAssignmentItems(Assignment assignment) {
		return new AssignmentDTO(assignment);
	}

	private Map<Integer, Boolean> getUserAssignmentMap(Collection<UserAssignment> userAssignments) {
		Map<Integer, Boolean> userAssignmentMap = new HashMap<Integer, Boolean>();
		for (UserAssignment userAssignment : userAssignments) {
			userAssignmentMap.put(userAssignment.getAssignment().getId(), true);
		}
		return userAssignmentMap;
	}
	
	private UserAssignment takeAssignment(String userId, Integer assignmentId) {
		Assignment assignment = assignmentDao.getAssignment(assignmentId);
		UserAssignment userAssignment = new UserAssignment(new User(userId), assignment, AssignmentStatus.IN_PROGRESS);
		userAssignmentDao.createUserAssignment(userAssignment);
		return userAssignment;
	}
}
