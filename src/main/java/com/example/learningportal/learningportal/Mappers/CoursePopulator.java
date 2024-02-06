package com.example.learningportal.learningportal.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.learningportal.learningportal.DTO.CourseDTO;
import com.example.learningportal.learningportal.Entities.Course;

@Mapper
public interface CoursePopulator {
	CoursePopulator INSTANCE = Mappers.getMapper(CoursePopulator.class);

	Course populateCourse(CourseDTO courseDTO);
}
