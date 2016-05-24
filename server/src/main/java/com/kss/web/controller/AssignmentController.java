package com.kss.web.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kss.domain.Assignment;
import com.kss.service.AssignmentService;

@Controller
@RequestMapping("/assignment")
public class AssignmentController {
	
	@Autowired
	private AssignmentService assignmentService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Assignment createAssignment(@RequestParam Assignment assignment) throws Exception{		
		return assignmentService.createAssignment(assignment);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Collection<Assignment> getAssignment(@RequestParam(required=false) Integer assignmentId) throws Exception{
		if (assignmentId == null) {
			return assignmentService.getAllAssignment();
		}
		return assignmentService.getAssignment(assignmentId);
	}
}
