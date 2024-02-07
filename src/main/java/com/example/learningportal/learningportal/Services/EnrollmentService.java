package com.example.learningportal.learningportal.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.learningportal.learningportal.DTO.EnrollmentDTO;
import com.example.learningportal.learningportal.DTO.EnrollmentResponseDTO;
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

	public EnrollmentResponseDTO addEnrollemnt(EnrollmentDTO enrollmentDTO) {

		Optional<User> optionalUser = userRepository.findById(enrollmentDTO.getUserId());
		Optional<Course> optionalCourse = courseRespository.findById(enrollmentDTO.getCourseId());

		if (optionalUser.isEmpty() || optionalCourse.isEmpty())
			return null;

		User user = optionalUser.get();
		Course course = optionalCourse.get();

		if (enrollmentRepository.existsByUserIdAndCourseId(user.getId(), course.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already enrolled");
		}

		Enrollment enrollment = EnrollmentPopulator.INSTANCE.populateEnrollment(enrollmentDTO);

		enrollment.setUser(user);
		enrollment.setCourse(course);

		enrollmentRepository.save(enrollment);

		EnrollmentResponseDTO enrollmentResponseDTO = EnrollmentPopulator.INSTANCE.enrollmentEntityToDTO(enrollment);
		enrollmentResponseDTO.setCourseId(course.getId());
		enrollmentResponseDTO.setUserId(user.getId());
		enrollmentResponseDTO.setUserName(user.getUserName());
		enrollmentResponseDTO.setCourseTitle(course.getCourseTitle());
		enrollmentResponseDTO.setCourseDescription(course.getCourseDescription());
		enrollmentResponseDTO.setEnrollmentDate(enrollment.getEnrollmentDate());

		return enrollmentResponseDTO;
	}
}
