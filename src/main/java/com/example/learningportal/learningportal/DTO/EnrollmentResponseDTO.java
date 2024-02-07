package com.example.learningportal.learningportal.DTO;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentResponseDTO {
	private int id;
	private int userId;
	private int courseId;
	private String userName;
	private String courseTitle;
	private String courseDescription;

	private Timestamp enrollmentDate;

	private boolean isFavourite;
}
