package com.kss.web.dto;

import com.kss.domain.ItemChoice;

public class ItemChoiceDTO {
	
	private int id;
	private String desc;
	
	public ItemChoiceDTO() {
	}
	
	public ItemChoiceDTO(ItemChoice choice) {
		this.id = choice.getId();
		this.desc = choice.getDesc();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int choiceId) {
		this.id = choiceId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
