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

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "KSS_ASSIGNMENT_ITEM_CHOICE")
@IdClass(value = ItemChoice.ItemChoiceKey.class)
public class ItemChoice {
	
	static class ItemChoiceKey implements Serializable {
		Integer id;
		AssignmentItem assignmentItem;
		

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		

		public AssignmentItem getAssignmentItem() {
			return assignmentItem;
		}

		public void setAssignmentItem(AssignmentItem assignmentItem) {
			this.assignmentItem = assignmentItem;
		}

	}

	@Id
	@Column(name = "CHOICE_ID")
	private Integer id;

	@Column(name = "CHOICE_VALUE")
	private String desc;

	@Column(name = "IS_CORRECT")
	private boolean isCorrect;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ASSIGNMENT_ID"),
		@JoinColumn(name = "ITEM_ID")
		
	})
	@Id
	@JsonIgnore
	private AssignmentItem assignmentItem;
	
	
	@JsonIgnore
	public AssignmentItem getAssignmentItem() {
		return assignmentItem;
	}
	

	public void setAssignmentItem(AssignmentItem assignmentItem) {
		this.assignmentItem = assignmentItem;
	}

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

	public Boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

}
