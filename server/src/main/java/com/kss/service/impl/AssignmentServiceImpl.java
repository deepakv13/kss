package com.kss.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kss.dao.impl.AssignmentDaoImpl;
import com.kss.domain.Assignment;
import com.kss.domain.AssignmentStatus;
import com.kss.service.api.AssignmentService;

@Service
@Transactional
public class AssignmentServiceImpl implements AssignmentService {
	
	@Autowired
	private AssignmentDaoImpl assignmentDao;

	private Assignment createAssignment(Assignment assignment) throws Exception {
		assignment.setModifiedAt(new Date());
		assignment.setStatus(AssignmentStatus.NEW);
		assignmentDao.createAssignment(assignment);
		return assignment;
	}
	
	public Collection<Assignment> getAssignment(int assignmentId) throws Exception{
		return Arrays.asList(assignmentDao.getAssignment(assignmentId));
	}
	
	public Collection<Assignment> getAllAssignment() throws Exception{
		return assignmentDao.getAllAssignment();
	}
	
	private Assignment updateAssignment(Assignment assignment){
		assignment.setStatus(AssignmentStatus.SAVED);
		return assignmentDao.updateAssignment(assignment);
	}
	
	public void deleteAssignment(int  assignmentId){
		assignmentDao.deleteAssignment(assignmentId);
	}

	public Assignment saveAssignment(Assignment assignment) throws Exception {
		Assignment updatedAssignment;
		if(assignment.getId() != null){
			updatedAssignment = this.updateAssignment(assignment);
		}else{
			updatedAssignment = this.createAssignment(assignment);
		}
		return updatedAssignment;
	}
	
	public Assignment publishAssignment(Assignment assignment) throws Exception {
		assignment.setModifiedAt(new Date());
		assignment.setStatus(AssignmentStatus.PUBLISHED);
		return assignmentDao.updateAssignment(assignment);
	}
	
}
