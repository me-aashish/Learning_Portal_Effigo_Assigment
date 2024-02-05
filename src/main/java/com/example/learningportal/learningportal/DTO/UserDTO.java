package com.example.learningportal.learningportal.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private int id;

	private String userName;

	private String userPassword;

	private String role;
}
