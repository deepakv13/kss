package com.kss.dao;

import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kss.dao.api.UserAssignmentDao;
import com.kss.dao.impl.AssignmentDaoImpl;
import com.kss.domain.UserAssignment;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-config.xml" })
@Transactional
public class UserAssignmentDaoTest {
	
	@Autowired
	AssignmentDaoImpl assignmentDaoImpl;
	
	@Autowired
	UserAssignmentDao userAssignmentDao;
	
	@Test
	public void getAllAttendedAssignmentsTest() throws Exception {
		Collection<UserAssignment> userAssignments = userAssignmentDao.getAllAttendedAssignments("AM35181");
		System.out.println(userAssignments.size());
	}
}
