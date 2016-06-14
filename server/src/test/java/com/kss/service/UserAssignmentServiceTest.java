package com.kss.service;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kss.service.api.UserAssignmentService;
import com.kss.utils.JsonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-config.xml" })
@Transactional
public class UserAssignmentServiceTest {
	
	@Autowired
	UserAssignmentService userAssignmentService;
	
	@Test
	public void getUserAssignmentsTest() {
		userAssignmentService.getUserAssignments("AM35181");
	}
	
	@Test
	public void getAndTakeUserAssignmentTest() throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println(JsonUtil.buildJsonFromObject(userAssignmentService.getOrTakeUserAssignment("AM35181", 76)));
	}
	
	@Test
	public void saveUserAssignmentItemsTest() {
		userAssignmentService.getUserAssignments("AM35181");
	}
}
