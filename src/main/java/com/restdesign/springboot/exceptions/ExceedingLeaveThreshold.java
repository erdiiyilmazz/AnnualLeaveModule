package com.restdesign.springboot.exceptions;

public class ExceedingLeaveThreshold extends Exception{
	private static final String message = "Exceeding Leave Threshold";
	public ExceedingLeaveThreshold(long remainingLeave, long requestedLeave) {
		super(message + ": Employee has " + remainingLeave + " days, requested " + requestedLeave + "");
	}
	public ExceedingLeaveThreshold() {
		super(message);
	}
}
