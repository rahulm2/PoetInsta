package com.mehra9.poetinsta.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mehra9.poetinsta.dto.UserResponse;
import com.mehra9.poetinsta.models.User;

public interface UserService extends UserDetailsService {

	User findByEmail(String email);

	User save(User registration);

	UserResponse getUserDetails(User user);

	byte[] getUserImage(User user);

	User updateUserImage(User user, byte[] fileBytes);
}
