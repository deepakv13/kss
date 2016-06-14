package com.kss.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "KSS_USER_ASSIGNMENT_ITEM")
@IdClass(value = UserAssignmentItem.UserAssignemtItemKey.class)
public class UserAssignmentItem {
	
	static class UserAssignemtItemKey implements Serializable {		
		User user;
		AssignmentItem assignmentItem;
		
		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public AssignmentItem getAssignmentItem() {
			return assignmentItem;
		}

		public void setAssignmentItem(AssignmentItem assignmentItem) {
			this.assignmentItem = assignmentItem;
		}		
	}
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns(value={
			@JoinColumn(name = "USER_ID")
	})
	private User user;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns(value={
			@JoinColumn(name = "ASSIGNMENT_ID", insertable = false, updatable = false),
			@JoinColumn(name = "ITEM_ID", insertable = false, updatable = false)
	})
	private AssignmentItem assignmentItem;
	
	@Column(name = "USER_INPUT")
	private String userInput;
	
	@Column(name = "SCORE")
	private String score;
	
	@Column(name = "COMMENTS")
	private String comments;
	
	@Column(name = "STATUS")
	private String status;
	
	public UserAssignmentItem() {
	}

	public UserAssignmentItem(User user,
			AssignmentItem assignmentItem, String userInput, String score,
			String comments, String status) {
		this.user = user;
		this.assignmentItem = assignmentItem;
		this.userInput = userInput;
		this.score = score;
		this.comments = comments;
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AssignmentItem getAssignmentItem() {
		return assignmentItem;
	}

	public void setAssignmentItem(AssignmentItem assignmentItem) {
		this.assignmentItem = assignmentItem;
	}

	public String getUserInput() {
		return userInput;
	}

	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
