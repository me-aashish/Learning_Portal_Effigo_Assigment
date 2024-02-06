package com.example.learningportal.learningportal.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class courseResponseDTO {

	private int id;
	private String courseTitle;
	private String courseDescription;
	private int authorId;
}
