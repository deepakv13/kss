package com.kss.web.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.kss.domain.AssignmentItem;
import com.kss.domain.ItemChoice;

public class AssignmentItemDTO {

	private int id;
	private String desc;
	private String itemType;
	private Integer weightage;
	
	private Map<String, String> answers = new HashMap<>();

	private List<ItemChoiceDTO> itemChoices = new ArrayList<ItemChoiceDTO>();

	public AssignmentItemDTO() {
	}

	public AssignmentItemDTO(AssignmentItem item,
			Map<String, String> userAnswers) {
		this.id = item.getId();
		this.desc = item.getDesc();
		this.itemType = item.getItemType();
		this.weightage = item.getWeightage();
		for (ItemChoice choice : item.getItemChoices()) {
			itemChoices.add(new ItemChoiceDTO(choice));
		}
		this.answers = userAnswers;
	}

	public int getId() {
		return id;
	}

	public void setId(int itemId) {
		this.id = itemId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public Integer getWeightage() {
		return weightage;
	}

	public void setWeightage(Integer weightage) {
		this.weightage = weightage;
	}

	public Map<String, String> getAnswers() {
		return answers;
	}

	public String getAnswersStr() {
		StringBuffer buf = new StringBuffer();
		String output = "";
		if (answers != null) {
			for (Entry<String, String> entrySet : answers.entrySet()) {
				String key = entrySet.getKey();
				String value = entrySet.getValue();
				if (value != null && !value.equals("null")) {
					buf.append(value);
					buf.append("#");
				}
			}
		}

		output = buf.toString();
		output = output.length() == 0 ? "" : output.substring(0, output.length() - 1);
		return output;
	}

	public void setAnswers(Map<String, String> answers) {
		this.answers = answers;
	}

	public List<ItemChoiceDTO> getItemChoices() {
		return itemChoices;
	}

	public void setItemChoices(List<ItemChoiceDTO> itemChoices) {
		this.itemChoices = itemChoices;
	}
}
