package com.kss.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AssignmentStatus {
		NEW ("NEW"),
		SAVED ("SAVED"),
		PUBLISHED ("PUBLISHED"),
		EXPIRED ("EXPIRED"),
		IN_PROGRESS ("IN PROGRESS"),
		COMPLETE ("COMPLETE");
		
		String value;
		private AssignmentStatus(String value) {
			this.value = value;
		}
		
	    @Override
	    public String toString() {
	        return this.value;
	    }
}
