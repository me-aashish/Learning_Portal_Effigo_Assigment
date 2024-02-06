package com.example.learningportal.learningportal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.learningportal.learningportal.DTO.CourseDTO;
import com.example.learningportal.learningportal.Entities.Course;
import com.example.learningportal.learningportal.Entities.User;
import com.example.learningportal.learningportal.Services.CourseService;
import com.example.learningportal.learningportal.Services.UserService;

@RestController
@RequestMapping("/api/v1")
public class CourseController {

	@Autowired
	CourseService courseService;

	@Autowired
	UserService userService;

	@PostMapping("/courses")
	public ResponseEntity<Course> addCourse(@RequestBody CourseDTO courseDTO) {
		User author = userService.getAuthorByIdAndRole(courseDTO.getAuthorId(), "AUTHOR");
		if (author == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Author not found with the given ID or not of role 'author'");
		}

		return new ResponseEntity<>(courseService.addCourse(courseDTO), HttpStatus.CREATED);
	}
}
