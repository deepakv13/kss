package com.kss.web.dto;

import java.util.Date;

import com.kss.domain.Assignment;
import com.kss.domain.AssignmentStatus;
import com.kss.domain.UserAssignment;

public class AssignmentDTO {

	private Integer id;
	private String name;
	private String desc;
	private AssignmentStatus status;
	private String owner;
	private Date modifiedAt;
	private String userId;

	public AssignmentDTO() {
	}

	public AssignmentDTO(Assignment assignment) {
		this.setId(assignment.getId());
		this.setName(assignment.getName());
		this.setDesc(assignment.getDesc());
		this.setOwner(assignment.getOwner());
		this.setModifiedAt(assignment.getModifiedAt());
		this.setStatus(assignment.getStatus());
	}
	
	public AssignmentDTO(UserAssignment userassignment) {
		this.setId(userassignment.getAssignment().getId());
		this.setName(userassignment.getAssignment().getName());
		this.setDesc(userassignment.getAssignment().getDesc());
		this.setOwner(userassignment.getAssignment().getOwner());
		this.setModifiedAt(userassignment.getModifiedAt());
		this.setStatus(userassignment.getStatus());
		this.setUserId(userassignment.getUser().getId());
	}
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public AssignmentStatus getStatus() {
		return status;
	}

	public void setStatus(AssignmentStatus status) {
		this.status = status;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssignmentDTO other = (AssignmentDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
