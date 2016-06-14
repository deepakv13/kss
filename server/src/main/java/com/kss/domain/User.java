package com.kss.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "KSS_USER")
public class User {
	
	@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	public static enum Role {
		ADMIN,
		USER
	}
	
	@Id
	@Column(name = "USER_ID")
	private String id;
	
	@Column(name = "USER_PASSWORD")
	private String password;
	
	@Column(name = "USER_ROLE")
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_AT")
	private Date createdAt;
	
	@Column(name = "STATUS")
	private String status;
	
//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//	private Set<UserAssignment> userAssignment;
	

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<UserAssignment> userAssignments = new HashSet<UserAssignment>(0);
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<UserAssignmentItem> userAssignmentItems = new HashSet<UserAssignmentItem>(0);
	
	public User() {
	}

	public User(String id, String password, Role role, Date createdAt, String status) {
		this.id = id;
		this.password = password;
		this.role = role;
		this.createdAt = createdAt;
		this.status = status;
	}

	public User(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<UserAssignment> getUserAssignments() {
		return userAssignments;
	}

	public void setUserAssignments(Set<UserAssignment> userAssignments) {
		for (UserAssignment userAssignment : userAssignments) {
			userAssignment.setUser(this);
		}
		this.userAssignments = userAssignments;
	}

	public Set<UserAssignmentItem> getUserAssignmentItems() {
		return userAssignmentItems;
	}

	public void setUserAssignmentItems(Set<UserAssignmentItem> userAssignmentItems) {
		for (UserAssignmentItem userAssignmentItem : userAssignmentItems) {
			userAssignmentItem.setUser(this);
		}
		this.userAssignmentItems = userAssignmentItems;
	}
	

/*	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", role=" + role
				+ ", createdAt=" + createdAt + ", status=" + status
				+ ", userAssignment=" + userAssignment + "]";
	}*/
}
