package com.kss.web.dto;

import java.util.List;

public class UserAssignmentCollectionDTO {
	
	private List<AssignmentDTO> attended;
	private List<AssignmentDTO> available;
	
	public UserAssignmentCollectionDTO(List<AssignmentDTO> attended,
			List<AssignmentDTO> available) {
		this.attended = attended;
		this.available = available;
	}
	
	public UserAssignmentCollectionDTO() {
	}

	public List<AssignmentDTO> getAttended() {
		return attended;
	}

	public void setAttended(List<AssignmentDTO> attended) {
		this.attended = attended;
	}

	public List<AssignmentDTO> getAvailable() {
		return available;
	}

	public void setAvailable(List<AssignmentDTO> available) {
		this.available = available;
	}
	
	
}
