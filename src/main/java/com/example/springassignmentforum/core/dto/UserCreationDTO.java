package com.example.springassignmentforum.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationDTO {
	private String name;
	private String phone;
	private String password;
}
