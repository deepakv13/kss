package com.kss.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kss.domain.UserAssignment;
import com.kss.domain.UserAssignmentItem;
import com.kss.service.api.UserAssignmentService;
import com.kss.utils.JsonUtil;
import com.kss.web.dto.UserAssignmentDTO;

@Controller
@RequestMapping("/userassignment")
@Transactional
public class UserAssignmentController {
	
	@Autowired
	UserAssignmentService userAssignmentService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getUserAssignments(@RequestParam String userId, @RequestParam(required=false) Integer assignmentId) throws Exception{
		String output = "";
		if (assignmentId == null) {
			output = JsonUtil.buildJsonFromObject(userAssignmentService.getUserAssignments(userId.toLowerCase()));
		}
		else {
			UserAssignment userAssignment = userAssignmentService.getOrTakeUserAssignment(userId.toLowerCase(), assignmentId);
			Set<UserAssignmentItem> items = userAssignment.getUser().getUserAssignmentItems();
			List<UserAssignmentItem> userAssignmentItems = new ArrayList<UserAssignmentItem>(items);
			output = JsonUtil.buildJsonFromObject(new UserAssignmentDTO(userAssignment,userAssignmentItems));
			System.out.println(output);
		}
		return output;
	}
/*	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String saveUserAssignmentItems(@RequestParam String userAssignmentStr) throws Exception {
		return JsonUtil.buildJsonFromObject(userAssignmentService.saveUserAssignmentItems((UserAssignment) JsonUtil.buildObjectFromJson(userAssignmentStr, UserAssignment.class)));
	}
	*/
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String saveOrSubmitUserAssignmentItems(@RequestBody String input) throws Exception {
		Map<String, Object> inputMap = JsonUtil.buildmapFromJson(input);
		String userAssignmentDTOStr = (String)JsonUtil.buildJsonFromObject(inputMap.get("userAssignmentDTOStr")); 
		boolean isSubmit = (boolean)inputMap.get("isSubmit");
		
		UserAssignmentDTO userAssignmentDTO = (UserAssignmentDTO) JsonUtil.buildObjectFromJson(userAssignmentDTOStr, UserAssignmentDTO.class);
		userAssignmentDTO = userAssignmentService.saveOrSubmitUserAssignment(userAssignmentDTO, isSubmit);
		
		
		return JsonUtil.buildJsonFromObject(userAssignmentDTO);
	}
	
	
}
