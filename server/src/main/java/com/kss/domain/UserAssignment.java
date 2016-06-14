package com.kss.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "KSS_USER_ASSIGNMENT")
@IdClass(value = UserAssignment.UserAssignmentKey.class)
public class UserAssignment {
	
	static class UserAssignmentKey implements Serializable {
		User user;
		Assignment assignment;

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Assignment getAssignment() {
			return assignment;
		}

		public void setAssignment(Assignment assignment) {
			this.assignment = assignment;
		}
	}
	
/*	@Id
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	private User user;*/
	
	@Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false, insertable = false, updatable = false)
	@JsonIgnore
	private User user;
	
/*	@Id
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ASSIGNMENT_ID")
	private Assignment assignment;*/
	
	@Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ASSIGNMENT_ID", nullable = false, insertable = false, updatable = false)
	private Assignment assignment;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "MODIFIED_AT")
	private Date modifiedAt;
	
	@Column(name = "SCORE")
	private Integer score;
	
	@Column(name = "COMMENTS")
	private String comments;
	
	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private AssignmentStatus status;
	
//	@OneToMany(mappedBy = "userAssignment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//	private Set<UserAssignmentItem> userAssignmentItems;
	
	public UserAssignment() {
	}

	public UserAssignment(User user, Assignment assignment, Date modifiedAt,
			Integer score, String comments, AssignmentStatus status,
			Set<UserAssignmentItem> userAssignmentItems) {
		this.user = user;
		this.assignment = assignment;
		this.modifiedAt = modifiedAt;
		this.score = score;
		this.comments = comments;
		this.status = status;
//		this.userAssignmentItems = userAssignmentItems;
	}
	
	public UserAssignment(User user, Assignment assignment, AssignmentStatus status) {
		this.user = user;
		this.assignment = assignment;
		this.modifiedAt = new Date();
		this.score = 0;
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

/*	public Set<UserAssignmentItem> getUserAssignmentItems() {
		return userAssignmentItems;
		return null;
	}*/

/*	public void setUserAssignmentItems(Set<UserAssignmentItem> userAssignmentItems) {
		this.userAssignmentItems = userAssignmentItems;
	}*/

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public AssignmentStatus getStatus() {
		return status;
	}

	public void setStatus(AssignmentStatus status) {
		this.status = status;
	}
}
