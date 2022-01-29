package com.restdesign.springboot.utils;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Util {
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	public static final ModelMapper MODEL_MAPPER = new ModelMapper();
}
