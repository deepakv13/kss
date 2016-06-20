package com.kss.web.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kss.domain.AssignmentItem;
import com.kss.domain.ItemChoice;
import com.kss.domain.UserAssignment;
import com.kss.domain.UserAssignmentItem;

public class UserAssignmentDTO {

	private String userId;
	private int assignmentId;
	private String status;
	private int score;
	private Date modifiedAt;
	private List<AssignmentItemDTO> assignmentItems = new ArrayList<AssignmentItemDTO>();

	public UserAssignmentDTO() {
	}

	public UserAssignmentDTO(UserAssignment userAssignment,
			List<UserAssignmentItem> userAssignmentItems) {
		this.userId = userAssignment.getUser().getId();
		this.assignmentId = userAssignment.getAssignment().getId();
		this.status = userAssignment.getAssignment().getStatus().toString();
		for (AssignmentItem item : userAssignment.getAssignment()
				.getAssignmentItems()) {
			Map<String, String> itemAnswers = new HashMap<String, String>();
			for (UserAssignmentItem userAssignmentItem : userAssignmentItems) {
				if ((item.getAssignment().getId() == userAssignmentItem
						.getAssignmentItem().getAssignment().getId())
						&& (item.getId() == userAssignmentItem
								.getAssignmentItem().getId())) {
					String userInput = userAssignmentItem.getUserInput();
					List<String> userInputArray = (userInput != null) ? Arrays
							.asList(userAssignmentItem.getUserInput()
									.split("#")) : Collections.EMPTY_LIST;

					itemAnswers = getItemAnswers(userInputArray,
							item.getItemChoices(), item.getItemType());

				}
			}
			this.assignmentItems.add(new AssignmentItemDTO(item, itemAnswers));
		}
	}

	private Map<String, String> getItemAnswers(List<String> userInputArray,
			Set<ItemChoice> itemChoices, String type) {

		Map<String, String> itemAnswers = new HashMap<String, String>();
		if (type.equalsIgnoreCase("Text Input") || type.equalsIgnoreCase("Single Choice")) {
			String userInput = userInputArray.size() == 0 ? "" : userInputArray
					.get(0);
			itemAnswers.put("0", userInput);

		} else {

			for (String userInput : userInputArray) {
				for (ItemChoice choice : itemChoices) {
					if (choice.getDesc().equalsIgnoreCase(userInput)) {
						itemAnswers.put(choice.getId().toString(), userInput);

					}
				}

			}
		}

		return itemAnswers;

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<AssignmentItemDTO> getAssignmentItems() {
		return assignmentItems;
	}

	public void setAssignmentItems(List<AssignmentItemDTO> assignmentItems) {
		this.assignmentItems = assignmentItems;
	}

	public AssignmentItemDTO getAssignmentItemDTO(int itemId) {
		AssignmentItemDTO itemDTO = null;
		for (AssignmentItemDTO item : assignmentItems) {
			if (item.getId() == itemId) {
				itemDTO = item;
				break;
			}
		}
		return itemDTO;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getScorePercentage() {

		int total = getTotalWeightage();
		int percentage = 0;
		if (total != 0) {
			percentage = (int) (((double) getScore() / (double) total) * 100);
		}

		return percentage + "%";

	}

	private int getTotalWeightage() {
		int total = 0;
		for (AssignmentItemDTO itemDTO : this.assignmentItems) {
			total += itemDTO.getWeightage();
		}
		return total;
	}
}
