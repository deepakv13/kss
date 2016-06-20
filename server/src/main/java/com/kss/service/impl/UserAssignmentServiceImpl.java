package com.kss.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kss.dao.api.UserAssignmentDao;
import com.kss.dao.api.UserDao;
import com.kss.dao.impl.AssignmentDaoImpl;
import com.kss.domain.Assignment;
import com.kss.domain.AssignmentItem;
import com.kss.domain.AssignmentStatus;
import com.kss.domain.ItemChoice;
import com.kss.domain.User;
import com.kss.domain.UserAssignment;
import com.kss.domain.UserAssignmentItem;
import com.kss.service.api.UserAssignmentService;
import com.kss.web.dto.AssignmentDTO;
import com.kss.web.dto.AssignmentItemDTO;
import com.kss.web.dto.UserAssignmentCollectionDTO;
import com.kss.web.dto.UserAssignmentDTO;

@Service
@Transactional
public class UserAssignmentServiceImpl implements UserAssignmentService{
	
	@Autowired
	AssignmentDaoImpl assignmentDao;
	
	@Autowired
	UserAssignmentDao userAssignmentDao;
	
	@Autowired
	UserDao userDao;
	
	public UserAssignmentCollectionDTO getUserAssignments(String userId) {
			return getGroupedAssignments(userAssignmentDao.getAllAttendedAssignments(userId), assignmentDao.getAllPublishedAssignment()); 
	}
	
	public UserAssignment getOrTakeUserAssignment(String userId, Integer assignmentId) {
		UserAssignment userAssignment = userAssignmentDao.getUserAssignment(userId, assignmentId);		
		if (userAssignment == null) {
			return takeAssignment(userId, assignmentId);
		}
		else {
			return userAssignment;
		}
	}

	public UserAssignment saveUserAssignmentItems(UserAssignment userAssignment) {
		userAssignmentDao.saveUserAssignment(userAssignment);
		return userAssignment;
	}

	private UserAssignmentCollectionDTO getGroupedAssignments(Collection<UserAssignment> userAssignments, Collection<Assignment> allAssignments) {
		List<AssignmentDTO> attendedAssignments = new LinkedList<AssignmentDTO>();
		List<AssignmentDTO> availableAssignments = new LinkedList<AssignmentDTO>();
		
		for (UserAssignment userAssignment : userAssignments) {
			AssignmentDTO assignmentDTO = new AssignmentDTO(userAssignment);
			attendedAssignments.add(assignmentDTO);
		}
		
		for (Assignment assignment : allAssignments) {
			AssignmentDTO assignmentDTO = new AssignmentDTO(assignment);
			availableAssignments.add(assignmentDTO);
		}
		availableAssignments.removeAll(attendedAssignments);
		return new UserAssignmentCollectionDTO(attendedAssignments, availableAssignments);
	}

	private UserAssignment takeAssignment(String userId, Integer assignmentId) {
		Assignment assignment = assignmentDao.getAssignment(assignmentId);
		UserAssignment userAssignment = new UserAssignment(new User(userId), assignment, AssignmentStatus.IN_PROGRESS);
		userAssignment.setModifiedAt(new Date());
		userAssignmentDao.createUserAssignment(userAssignment);
		return userAssignment;
	}

	@Override
	public UserAssignmentDTO saveOrSubmitUserAssignment(UserAssignmentDTO userAssignmentDTO, boolean isSubmit) {
		UserAssignment userAssignment = userAssignmentDao.getUserAssignment(userAssignmentDTO.getUserId(), userAssignmentDTO.getAssignmentId());
		User user = userAssignment.getUser();
		
		Assignment assignment = userAssignment.getAssignment();
		int score = 0; 
		for (AssignmentItem item : assignment.getAssignmentItems()) {
			AssignmentItemDTO aiDTO = userAssignmentDTO.getAssignmentItemDTO(item.getId());
			int uiScore = calculateScore(item, aiDTO);
			score += uiScore;			
			UserAssignmentItem uai = getExistingUserAssignmentItem(user.getUserAssignmentItems(), item);
			if (uai == null) {
				uai = new UserAssignmentItem(user, item, aiDTO.getAnswersStr(), null, null, null);
				user.addUserAssignmentItem(uai);
			}				
			else {
				uai.setUserInput(aiDTO.getAnswersStr());
			}
		}	
		Date date = new Date();
		userAssignment.setModifiedAt(date);
		userAssignmentDTO.setModifiedAt(date);
		
		if (isSubmit) {
			userAssignment.setScore(score);
			userAssignment.setStatus(AssignmentStatus.COMPLETED);
			
			
			userAssignmentDTO.setScore(score);
			userAssignmentDTO.setStatus(AssignmentStatus.COMPLETED.toString());
		}
			
		userDao.saveUser(user);
		
		return userAssignmentDTO; 
	}
	
	private UserAssignmentItem getExistingUserAssignmentItem(Set<UserAssignmentItem> userAssignmentItems, AssignmentItem item) {
		for (UserAssignmentItem userAssignmentItem : userAssignmentItems) {
			if (userAssignmentItem.getAssignmentItem().getAssignment().getId() == item.getAssignment().getId() && userAssignmentItem.getAssignmentItem().getId() == item.getId()) {
				return userAssignmentItem;
			}
		}
		return null;
	}

	private int calculateScore(AssignmentItem item, AssignmentItemDTO aiDTO) {
		Integer score = item.getWeightage();
		for (ItemChoice choice : item.getItemChoices()) {
			if (choice.getIsCorrect().equalsIgnoreCase("correct") && !isChoicePresentInUserAnswers(choice.getDesc(), aiDTO.getAnswers().values())) {
				score = 0;
				break;
			}
			else if (choice.getIsCorrect().equalsIgnoreCase("incorrect") && isChoicePresentInUserAnswers(choice.getDesc(), aiDTO.getAnswers().values())) {
				score = 0;
				break;
			}
		}
		return score;
	}
	
	private boolean isChoicePresentInUserAnswers(String choice,Collection<String> answers) {
		for(String answer:answers){			
			if(answer!=null && answer.equalsIgnoreCase(choice)){
				return true;
			}
		}
		return false;
	}
}
