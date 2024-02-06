package com.example.learningportal.learningportal.Repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.example.learningportal.learningportal.Entities.User;

@Repository
public interface UserRepository extends JpaRepositoryImplementation<User, Integer> {

	@Query("SELECT u.id AS userID, u.userName AS userName, u.role AS userRole FROM User u")
	List<Map<String, Object>> findAllUsers();

}
