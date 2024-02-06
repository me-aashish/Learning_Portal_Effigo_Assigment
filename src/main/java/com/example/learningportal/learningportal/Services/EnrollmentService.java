package com.example.learningportal.learningportal.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learningportal.learningportal.DTO.EnrollmentDTO;
import com.example.learningportal.learningportal.Entities.Course;
import com.example.learningportal.learningportal.Entities.Enrollment;
import com.example.learningportal.learningportal.Entities.User;
import com.example.learningportal.learningportal.Mappers.EnrollmentPopulator;
import com.example.learningportal.learningportal.Repositories.CourseRespository;
import com.example.learningportal.learningportal.Repositories.EnrollmentRepository;
import com.example.learningportal.learningportal.Repositories.UserRepository;

@Service
public class EnrollmentService {

	@Autowired
	EnrollmentRepository enrollmentRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CourseRespository courseRespository;

	public Enrollment addEnrollemnt(EnrollmentDTO enrollmentDTO) {

		Optional<User> optionalUser = userRepository.findById(enrollmentDTO.getUserId());
		Optional<Course> optionalCourse = courseRespository.findById(enrollmentDTO.getCourseId());

		if (optionalUser.isEmpty() || optionalCourse.isEmpty())
			return null;

		User user = optionalUser.get();
		Course course = optionalCourse.get();

		Enrollment enrollment = EnrollmentPopulator.INSTANCE.populateEnrollment(enrollmentDTO);
		enrollment.setUser(user);
		enrollment.setCourse(course);

		return enrollmentRepository.save(enrollment);

	}
}