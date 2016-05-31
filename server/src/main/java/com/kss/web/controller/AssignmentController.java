package com.kss.web.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kss.domain.Assignment;
import com.kss.service.AssignmentService;
import com.kss.utils.JsonUtil;

@Controller
@RequestMapping("/assignment")
public class AssignmentController {
	
	@Autowired
	private AssignmentService assignmentService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String saveAssignment(@RequestBody String assignmentStr) throws Exception{
		Assignment assignment = (Assignment) JsonUtil.buildObjectFromJson(assignmentStr, Assignment.class);
		return JsonUtil.buildJsonFromObject(assignmentService.saveAssignment(assignment));
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getAssignment(@RequestParam(required=false) Integer assignmentId) throws Exception{
		if (assignmentId == null) {
			return JsonUtil.buildJsonFromObject(assignmentService.getAllAssignment());
		}
		return JsonUtil.buildJsonFromObject(assignmentService.getAssignment(assignmentId));
	}
	
	
	@RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public void deleteAssignment(@RequestParam Integer assignmentId) throws Exception{
	  assignmentService.deleteAssignment(assignmentId);
	}
	
	
	
}
