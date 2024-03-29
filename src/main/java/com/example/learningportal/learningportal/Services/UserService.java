package com.example.learningportal.learningportal.Services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learningportal.learningportal.DTO.UserDTO;
import com.example.learningportal.learningportal.DTO.UserResponseDTO;
import com.example.learningportal.learningportal.Entities.User;
import com.example.learningportal.learningportal.Mappers.UserPopulator;
import com.example.learningportal.learningportal.Repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public UserResponseDTO addUser(UserDTO userDTO) {
		User user = UserPopulator.INSTANCE.populateUser(userDTO);
		userRepository.save(user);
		UserResponseDTO userResponseDTO = UserPopulator.INSTANCE.userEntityToDTO(user);
		return userResponseDTO;
	}

	public User getAuthorByIdAndRole(int authorId, String str) {
		Optional<User> optionalUser = userRepository.findById(authorId);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			if (user.getRole().equals(str)) {
				return user;
			}
		}

		return null;
	}

	public List<Map<String, Object>> getUsers() {
		return userRepository.findAllUsers();
	}
}
