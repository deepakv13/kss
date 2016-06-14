package com.kss.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kss.dao.impl.AssignmentDaoImpl;
import com.kss.domain.Assignment;
import com.kss.domain.AssignmentItem;
import com.kss.domain.ItemChoice;
import com.kss.utils.JsonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-config.xml" })
@Transactional
//@Ignore
public class AssignmentDaoTest {

	@Autowired
	private AssignmentDaoImpl assignmentDao;

	@Test
	public void shouldCreateAssignment() throws Exception {
		Assignment assignment = getDummyAssignment();
		assertNull(assignment.getId());
		assignmentDao.createAssignment(assignment);
		assertNotNull(assignment.getId());
		System.out.println(JsonUtil.buildJsonFromObject(assignment));
	}
	
	@Test		
	public void shouldUpdateAssignment() throws Exception {
		Assignment assignment = assignmentDao.getAssignment(102);
		
		
		ItemChoice c1 = new ItemChoice();
		c1.setDesc("India");
		ItemChoice c2 = new ItemChoice();
		c2.setDesc("USA");
		ItemChoice c3 = new ItemChoice();
		c3.setDesc("Russia");
		c3.setIsCorrect("Y");
		ItemChoice c4 = new ItemChoice();
		c4.setDesc("Canada");
		List<ItemChoice> choices1 = Arrays.asList(c1, c2, c3, c4);
		List<ItemChoice> choices2 = Arrays.asList(c1, c2);

		AssignmentItem item1 = new AssignmentItem();
		item1.setCreatedAt(new Date());
		item1.setDesc("Largest Country");
		item1.setItemType("Single Choice");
		item1.setStatus("OPEN");
		item1.setWeightage(10);
		item1.setItemChoices(new HashSet<ItemChoice>(choices1));
		
		AssignmentItem item2 = new AssignmentItem();
		item1.setCreatedAt(new Date());
		item1.setDesc("Delete Largest Country");
		item1.setItemType("Delete Single Choice");
		item1.setStatus("OPEN");
		item1.setWeightage(20);
		item1.setItemChoices(new HashSet<ItemChoice>(choices2));
		
		assignment.addItem(item1);
		assignment.addItem(item2);
		
		assignmentDao.updateAssignment(assignment);
	}
	
	@Test
	public void shouldGetAssignment() throws Exception{
		Assignment assignment = assignmentDao.getAssignment(52);
		System.out.print(assignment);
	}
	
	@Test
	public void shouldGetAllAssignment() throws Exception{
		Collection<Assignment> assignment = assignmentDao.getAllAssignment();
		System.out.print(assignment.size());
	}
	
	@Test
	public void shouldDeleteAssignment() throws Exception{
		Assignment assignment  = assignmentDao.getAssignment(70);
		assertNotNull(assignment);
		assignmentDao.deleteAssignment(assignment.getId());
		
		assignment  = assignmentDao.getAssignment(assignment.getId());
		assertNull(assignment);
	}
	

	private Assignment getDummyAssignment() {
		
		Assignment assignment = new Assignment();
		assignment.setDesc("This is a test assignment - 2");
		assignment.setName("Test Assignment2");
		assignment.setOwner("GP16816");
		
		AssignmentItem item1 = new AssignmentItem();
		item1.setDesc("Which is the largest state of India?");
		item1.setItemType("Single Choice");

		ItemChoice c1 = new ItemChoice();
		c1.setDesc("Delhi");
		c1.setIsCorrect("y");
		ItemChoice c2 = new ItemChoice();
		c2.setDesc("Maharashtra");
		ItemChoice c3 = new ItemChoice();
		c3.setDesc("MP");
		ItemChoice c4 = new ItemChoice();
		c4.setDesc("Punjab");
		List<ItemChoice> choices1 = Arrays.asList(c1, c2, c3, c4);

		item1.setWeightage(10);
		item1.setItemChoices(new HashSet<ItemChoice>(choices1));

		List<AssignmentItem> items = Arrays.asList(item1);



		assignment.setAssignmentItems(new HashSet(items));

		return assignment;
	}

}

