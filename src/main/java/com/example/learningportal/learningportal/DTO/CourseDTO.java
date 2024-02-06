package com.example.learningportal.learningportal.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

	private int id;

	private int authorId;

	private String courseTitle;

	private String courseDescription;
}
