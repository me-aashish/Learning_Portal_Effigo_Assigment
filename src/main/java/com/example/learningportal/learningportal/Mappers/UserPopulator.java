package com.example.learningportal.learningportal.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.learningportal.learningportal.DTO.UserDTO;
import com.example.learningportal.learningportal.Entities.User;

@Mapper
public interface UserPopulator {
	UserPopulator INSTANCE = Mappers.getMapper(UserPopulator.class);

	User populateUser(UserDTO userDTO);
}
