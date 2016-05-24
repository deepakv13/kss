package com.kss.domain;

public class Assignment {
	
	private int id;
	private String name;
	private String desc;
	private String status;
	private String owner;
	
	private Assignment() {}
	
	public Assignment(String name, String desc, String status, String owner) {
		super();
		this.name = name;
		this.desc = desc;
		this.status = status;
		this.owner = owner;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
}
