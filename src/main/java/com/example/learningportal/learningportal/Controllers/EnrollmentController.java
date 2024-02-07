package com.example.learningportal.learningportal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.learningportal.learningportal.DTO.EnrollmentDTO;
import com.example.learningportal.learningportal.DTO.EnrollmentResponseDTO;
import com.example.learningportal.learningportal.Services.EnrollmentService;

@RestController
@RequestMapping("/api/v1")
public class EnrollmentController {

	@Autowired
	EnrollmentService enrollmentService;

	@PostMapping("/enrollments")
	public ResponseEntity<EnrollmentResponseDTO> addEnrollment(@RequestBody EnrollmentDTO enrollmentDTO) {

		EnrollmentResponseDTO enrollment = enrollmentService.addEnrollemnt(enrollmentDTO);

		if (enrollment == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course or user with given ids not found");

		return new ResponseEntity<>(enrollment, HttpStatus.CREATED);
	}

	@PostMapping("/favourites")
	public ResponseEntity<EnrollmentResponseDTO> addFavourite(@RequestBody EnrollmentDTO enrollmentDTO) {

		EnrollmentResponseDTO enrollmentResponseDTO = enrollmentService.addFavourite(enrollmentDTO);

		if (enrollmentResponseDTO == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");

		return new ResponseEntity<>(enrollmentResponseDTO, HttpStatus.OK);
	}

}
