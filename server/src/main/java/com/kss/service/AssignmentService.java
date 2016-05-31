package com.kss.service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kss.dao.AssignmentDaoImpl;
import com.kss.domain.Assignment;

@Service
@Transactional
public class AssignmentService {
	
	@Autowired
	private AssignmentDaoImpl assignmentDao;

	private Assignment createAssignment(Assignment assignment) throws Exception{	
		assignment.setStatus("NOT_SUBMITTED");
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
		return assignmentDao.updateAssignment(assignment);
	}
	
	public void deleteAssignment(int  assignmentId){
		assignmentDao.deleteAssignment(assignmentId);
	}

	public Assignment saveAssignment(Assignment assignment) {
		if(assignment.getId()!=null){
			assignmentDao.updateAssignment(assignment);
		}else{
			assignmentDao.createAssignment(assignment);
		}
		return assignment;
	}
	
}
