package com.example.learningportal.learningportal.Entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enrollments")
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enrollment_id")
	private int id;

	@Column(name = "enrollment_date")
	private Timestamp enrollmentDate;

	@PrePersist
	public void prePersist() {
		this.enrollmentDate = new Timestamp(System.currentTimeMillis());
	}

	public Timestamp getEnrollmentDate() {
		return this.enrollmentDate;
	}

	//for enrollments to user
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	//for enrollments to course
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
}
