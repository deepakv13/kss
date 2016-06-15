package com.kss.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "KSS_ASSIGNMENT")
public class Assignment {
	
	@Id
	@Column(name = "ASSIGNMENT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "assignemt_id_seq")
	@SequenceGenerator(name = "assignemt_id_seq", sequenceName = "KSS_ASSIGNMENT_SEQ", allocationSize = 1)
	private Integer id;

	@Column(name = "ASSIGNMENT_NAME")
	private String name;

	@Column(name = "ASSIGNMENT_DESC")
	private String desc;

	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private AssignmentStatus status;

	@Column(name = "OWNER")
	private String owner;

	@Temporal(TemporalType.DATE)
	@Column(name = "MODIFIED_AT")
	private Date modifiedAt;
	
	@JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "assignment")
	private Set<UserAssignment> userAssignments = new HashSet<UserAssignment>(0);
	
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "assignment")
	private Set<AssignmentItem> assignmentItems = new HashSet<AssignmentItem>(0);
	
	public Assignment() {
	}

	public Assignment(String name, String desc, AssignmentStatus status, String owner) {
		super();
		this.name = name;
		this.desc = desc;
		this.status = status;
		this.owner = owner;
		this.modifiedAt = new Date();
	}
	
	public Set<UserAssignment> getUserAssignments() {
		return userAssignments;
	}

	public void setUserAssignments(Set<UserAssignment> userAssignments) {
		this.userAssignments = userAssignments;
	}

	public Assignment(Integer id) {
		this.id = id;
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

	public Set<AssignmentItem> getAssignmentItems() {
		return assignmentItems;
	}

	public void setAssignmentItems(Set<AssignmentItem> assignmentItems) {
		List<AssignmentItem> assignmentItemList = new ArrayList<AssignmentItem>(assignmentItems);
		Collections.sort(assignmentItemList, new Comparator<AssignmentItem>() {
			public int compare(AssignmentItem a1, AssignmentItem a2) {
				if (a1.getId() == null) {
					return 1;
				}
				else if (a2.getId() == null) {
					return -1;
				}
				return a1.getId() - a2.getId();
			}
		});
		int maxId= 0;
		for(int i = 0; i < assignmentItemList.size(); i++){
			AssignmentItem item = assignmentItemList.get(i);
			if(item.getId()!=null){
				maxId= item.getId();  //as list is sorted ,can directly do this
			}else if (item.getId() == null) {
				maxId = maxId+1;
				item.setId(maxId);
				
				item.setCreatedAt(new Date());
			}
			item.setAssignment(this);
		}
		this.assignmentItems = assignmentItems;
	}

	public void addItem(AssignmentItem item) {
		item.setId(this.getAssignmentItems().size()+1);
		item.setAssignment(this);
		this.assignmentItems.add(item);
	}
}
