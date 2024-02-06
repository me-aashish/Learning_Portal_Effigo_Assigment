package com.example.learningportal.learningportal.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.learningportal.learningportal.DTO.CourseDTO;
import com.example.learningportal.learningportal.Entities.Course;

@Repository
public interface CourseRespository extends JpaRepositoryImplementation<Course, Integer> {

	@Query("SELECT c as Matched_Results FROM Course c WHERE c.courseTitle = :courseTitle")
	List<Course> findByTitle(@Param("courseTitle") String courseTitle);

	@Query("SELECT NEW com.example.learningportal.learningportal.DTO.CourseDTO(c.id, u.id, c.courseTitle, c.courseDescription) "
			+ "FROM Course c JOIN c.author u")
	List<CourseDTO> getCourses();

}
