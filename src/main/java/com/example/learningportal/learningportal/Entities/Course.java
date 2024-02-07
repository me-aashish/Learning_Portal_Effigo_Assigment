package com.example.learningportal.learningportal.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private int id;

	@Column(name = "course_title")
	private String courseTitle;

	@Column(name = "course_description")
	private String courseDescription;

	//for courses to user
	@ManyToOne
	@JoinColumn(name = "author_id")
	private User author;

	//for course to enrollments
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Enrollment> enrollments;

}
