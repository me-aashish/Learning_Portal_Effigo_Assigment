package com.example.learningportal.learningportal.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learningportal.learningportal.DTO.CourseDTO;
import com.example.learningportal.learningportal.DTO.CourseResponseDTO;
import com.example.learningportal.learningportal.Entities.Course;
import com.example.learningportal.learningportal.Entities.User;
import com.example.learningportal.learningportal.Mappers.CoursePopulator;
import com.example.learningportal.learningportal.Repositories.CourseRespository;
import com.example.learningportal.learningportal.Repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class CourseService {

	@Autowired
	CourseRespository courseRespository;

	@Autowired
	UserRepository userRepository;

	public CourseResponseDTO addCourse(CourseDTO courseDTO) {
		Optional<User> optionalUser = userRepository.findById(courseDTO.getAuthorId());
		Course course = CoursePopulator.INSTANCE.populateCourse(courseDTO);
		if (optionalUser.isPresent()) {
			User author = optionalUser.get();
			course.setAuthor(author);
		}
		courseRespository.save(course);
		CourseResponseDTO courseResponseDTO = CoursePopulator.INSTANCE.courseEntityToDTO(course);
		courseResponseDTO.setAuthorId(course.getAuthor().getId());
		return courseResponseDTO;

	}

	@Transactional
	public void updateCourse(int courseId, CourseDTO courseDTO) {
		Optional<Course> optionalCourse = courseRespository.findById(courseId);

		if (optionalCourse.isEmpty())
			return;

		Course course = optionalCourse.get();
		course.setCourseDescription(courseDTO.getCourseDescription());
		course.setCourseTitle(courseDTO.getCourseTitle());
		courseRespository.save(course);

	}

	public List<CourseDTO> getCourses() {
		return courseRespository.getCourses();
	}

	public Optional<CourseResponseDTO> getSearchedCourses(CourseDTO courseDTO) {
		return courseRespository.findByTitle(courseDTO.getCourseTitle());
	}
}
