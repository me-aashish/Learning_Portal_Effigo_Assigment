package com.example.learningportal.learningportal.Mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.learningportal.learningportal.DTO.EnrollmentDTO;
import com.example.learningportal.learningportal.DTO.EnrollmentResponseDTO;
import com.example.learningportal.learningportal.DTO.FavouriteResponseDTO;
import com.example.learningportal.learningportal.Entities.Enrollment;

@Mapper
public interface EnrollmentPopulator {
	EnrollmentPopulator INSTANCE = Mappers.getMapper(EnrollmentPopulator.class);

	Enrollment populateEnrollment(EnrollmentDTO enrollmentDTO);

	EnrollmentResponseDTO enrollmentEntityToDTO(Enrollment enrollment);

	FavouriteResponseDTO favouriteResponseEntityToDTO(Enrollment enrollment);

	List<EnrollmentResponseDTO> enrollmentListEntityToDTOList(List<Enrollment> enrollments);
}
