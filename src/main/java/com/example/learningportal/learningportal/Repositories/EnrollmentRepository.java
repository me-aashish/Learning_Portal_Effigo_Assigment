package com.example.learningportal.learningportal.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.example.learningportal.learningportal.Entities.Enrollment;

@Repository
public interface EnrollmentRepository extends JpaRepositoryImplementation<Enrollment, Integer> {

	@Query("SELECT COUNT(e) > 0 FROM Enrollment e WHERE e.user.id = :userId AND e.course.id = :courseId")
	boolean existsByUserIdAndCourseId(int userId, int courseId);

}
