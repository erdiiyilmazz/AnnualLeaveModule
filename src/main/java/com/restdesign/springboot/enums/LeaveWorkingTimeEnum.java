package com.restdesign.springboot.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LeaveWorkingTimeEnum {
	
	ENTRY(0, 1, 5),
	ONETOFIVE(1, 5, 15),
	FIVETOTEN(5, 10, 18),
	ABOVETEN(10, null, 24);
	
	private Integer minYears;
	private Integer maxYears;
	private Integer threshold;
	
	LeaveWorkingTimeEnum(Integer minYears, Integer maxYears, Integer threshold) {
		this.minYears = minYears;
		this.maxYears = maxYears;
		this.threshold = threshold;
	}

	public Integer getMinYears() {
		return minYears;
	}

	public void setMinYears(int minYears) {
		this.minYears = minYears;
	}

	public Integer getMaxYears() {
		return maxYears;
	}

	public void setMaxYears(int maxYears) {
		this.maxYears = maxYears;
	}

	public Integer getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	
	
}
