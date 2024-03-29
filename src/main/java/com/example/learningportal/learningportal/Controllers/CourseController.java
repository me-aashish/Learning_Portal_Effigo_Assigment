package com.example.learningportal.learningportal.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.learningportal.learningportal.DTO.CourseDTO;
import com.example.learningportal.learningportal.DTO.CourseResponseDTO;
import com.example.learningportal.learningportal.Entities.Course;
import com.example.learningportal.learningportal.Entities.User;
import com.example.learningportal.learningportal.Mappers.CoursePopulator;
import com.example.learningportal.learningportal.Repositories.CourseRespository;
import com.example.learningportal.learningportal.Services.CourseService;
import com.example.learningportal.learningportal.Services.UserService;

@RestController
@RequestMapping("/api/v1")
public class CourseController {

	@Autowired
	CourseService courseService;

	@Autowired
	UserService userService;

	@Autowired
	CourseRespository courseRespository;

	@PostMapping("/courses")
	public ResponseEntity<CourseResponseDTO> addCourse(@RequestBody CourseDTO courseDTO) {
		User author = userService.getAuthorByIdAndRole(courseDTO.getAuthorId(), "AUTHOR");
		if (author == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Author not found with the given ID or not of role 'author'");
		}

		return new ResponseEntity<>(courseService.addCourse(courseDTO), HttpStatus.CREATED);
	}

	@PutMapping("/courses/{courseId}")
	public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable int courseId,
			@RequestBody CourseDTO courseDTO) {
		System.out.println(courseId);

		Optional<Course> course = courseRespository.findById(courseId);
		if (course == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found");
		courseService.updateCourse(courseId, courseDTO);
		CourseResponseDTO courseResponseDTO = CoursePopulator.INSTANCE.courseEntityToDTO(course.get());
		courseResponseDTO.setAuthorId(course.get().getAuthor().getId());
		return new ResponseEntity<>(courseResponseDTO, HttpStatus.OK);

	}

	@GetMapping("/courses")
	public ResponseEntity<List<CourseDTO>> getCourses() {

		List<CourseDTO> courses = courseService.getCourses();

		return new ResponseEntity<>(courses, HttpStatus.OK);
	}

	@GetMapping("/course")
	public ResponseEntity<Optional<CourseResponseDTO>> getSearchedCourse(@RequestBody CourseDTO courseDTO) {
		Optional<CourseResponseDTO> courses = courseService.getSearchedCourses(courseDTO);

		if (courses.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found");
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}
}
