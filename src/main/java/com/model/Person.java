package com.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
	@JsonProperty("id")
	private String id;

	//@JsonProperty("first_name")
	private String firstName;

	//@JsonProperty("last_name")
	private String lastName;

	//@JsonProperty("age")
	private int age;
	
	
}
