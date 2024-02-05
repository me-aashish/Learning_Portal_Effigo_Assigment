package com.example.learningportal.learningportal.Repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.example.learningportal.learningportal.Entities.User;

@Repository
public interface UserRepository extends JpaRepositoryImplementation<User, Integer> {

}
