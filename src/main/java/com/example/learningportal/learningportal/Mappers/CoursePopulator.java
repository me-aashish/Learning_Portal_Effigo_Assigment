package com.example.learningportal.learningportal.Mappers;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.learningportal.learningportal.DTO.CourseDTO;
import com.example.learningportal.learningportal.DTO.CourseResponseDTO;
import com.example.learningportal.learningportal.Entities.Course;

@Mapper
public interface CoursePopulator {
	CoursePopulator INSTANCE = Mappers.getMapper(CoursePopulator.class);

	Course populateCourse(CourseDTO courseDTO);

	CourseResponseDTO courseEntityToDTO(Course course);

	List<CourseResponseDTO> courseListEntityToDTO(List<Course> courses);

	CourseResponseDTO optionalCourseEntityToDTO(Optional<Course> course);

}
