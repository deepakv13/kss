package com.kss.service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.kss.domain.Assignment;

@Service
public class AssignmentService {

	public Assignment createAssignment(Assignment assignment) throws Exception{
		assignment.setId(1);
		assignment.setStatus("NOT SUBMITTED");
		return assignment;
	}
	
	public Collection<Assignment> getAssignment(int assignmentId) throws Exception{
		Assignment a = new Assignment("dummy", "dummy desc", "NOT SUBMITTED", "dummy1");
		a.setId(assignmentId);
		return Arrays.asList(a);
	}
	
	public Collection<Assignment> getAllAssignment() throws Exception{
		Assignment a1 = new Assignment("dummy1", "dummy1 desc", "NOT SUBMITTED", "dummy1");
		a1.setId(1);
		
		Assignment a2 = new Assignment("dummy2", "dummy2 desc", "NOT SUBMITTED", "dummy2");
		a2.setId(2);
		
		Assignment a3 = new Assignment("dummy3", "dummy3 desc", "NOT SUBMITTED", "dummy3");
		a3.setId(3);
		
		Assignment a4 = new Assignment("dummy4", "dummy4 desc", "NOT SUBMITTED", "dummy4");
		a4.setId(4);
		
		return Arrays.asList(a1, a2, a3, a4);
	}
}
