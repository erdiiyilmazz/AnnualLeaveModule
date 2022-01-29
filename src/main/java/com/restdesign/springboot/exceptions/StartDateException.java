package com.restdesign.springboot.exceptions;

public class StartDateException extends Exception{
	private static final String prompt = "StartDateException";
	public StartDateException(String startDate, String endDate) {
		super(prompt + ": '" + startDate + " is greater than " + endDate + "'");
	}
	public StartDateException() {
		super(prompt);
	}
}
