package com.kss.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kss.domain.UserAssignment;
import com.kss.service.api.UserAssignmentService;
import com.kss.utils.JsonUtil;

@Controller
@RequestMapping("/userassignment")
@Transactional
public class UserAssignmentController {
	
	@Autowired
	UserAssignmentService userAssignmentService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getUserAssignments(@RequestParam String userId, @RequestParam(required=false) Integer assignmentId) throws Exception{
		if (assignmentId == null) {
			return JsonUtil.buildJsonFromObject(userAssignmentService.getUserAssignments(userId.toLowerCase()));
		}
		return JsonUtil.buildJsonFromObject(userAssignmentService.getOrTakeUserAssignment(userId.toLowerCase(), assignmentId));
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String saveUserAssignmentItems(@RequestParam String userAssignmentStr) throws Exception {
		return JsonUtil.buildJsonFromObject(userAssignmentService.saveUserAssignmentItems((UserAssignment) JsonUtil.buildObjectFromJson(userAssignmentStr, UserAssignment.class)));
	}
	
//	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
//	@ResponseBody
//	public String saveOrSubmitUserAssignmentItems(@RequestParam String userId, @RequestParam String userAssignmentStr, @RequestParam boolean isSubmit) throws Exception{
//		return JsonUtil.buildJsonFromObject(userAssignmentService.saveOrSubmitUserAssignmentItems(userId, (UserAssignment) JsonUtil.buildObjectFromJson(userAssignmentStr, UserAssignment.class), isSubmit));
//	}
	
	
}
