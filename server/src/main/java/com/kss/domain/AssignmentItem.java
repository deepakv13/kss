package com.kss.domain;

import java.io.Serializable;
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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
@Entity
@Table(name = "KSS_ASSIGNMENT_ITEM")
@IdClass(value = AssignmentItem.AssignemtItemKey.class)
public class AssignmentItem {

	static class AssignemtItemKey implements Serializable {
		Integer id;
		Assignment assignment;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Assignment getAssignment() {
			return assignment;
		}

		public void setAssignment(Assignment assignment) {
			this.assignment = assignment;
		}

	}

	@Id
	@Column(name = "ITEM_ID")
	private Integer id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ASSIGNMENT_ID")
	@Id
	private Assignment assignment;

	@Column(name = "ITEM_DESC")
	private String desc;

	@Column(name = "ITEM_TYPE")
	private String itemType;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_AT")
	private Date createdAt;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "WEIGHTAGE")
	private Integer weightage;

	@OneToMany(mappedBy = "assignmentItem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<ItemChoice> itemChoices;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getWeightage() {
		return weightage;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
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

	@JsonIgnore
	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	public void setWeightage(Integer weightage) {
		this.weightage = weightage;
	}	

	public Set<ItemChoice> getItemChoices() {
		return itemChoices;
	}

	public void setItemChoices(Set<ItemChoice> itemChoices) {
		List<ItemChoice> itemChoiceList = new ArrayList<ItemChoice>(itemChoices);
		Collections.sort(itemChoiceList, new Comparator<ItemChoice>() {
			public int compare(ItemChoice a1, ItemChoice a2) {
				if (a1.getId() == null) {
					return 1;
				}
				else if (a2.getId() == null) {
					return -1;
				}
				return a1.getId() - a2.getId();
			}
		});
		for(int i = 0;i < itemChoiceList.size(); i++){
			ItemChoice choice = itemChoiceList.get(i);
			if (choice.getId() == null) {
				choice.setId(i+1);	
			}
			choice.setAssignmentItem(this);
		}
		this.itemChoices = itemChoices;
	}
}
