package com.mehra9.poetinsta.controller;

import static com.mehra9.poetinsta.utils.ProjectConstants.USER_INSTANCE_TYPE_EXCEPTION;
import static com.mehra9.poetinsta.utils.ProjectConstants.USER_NOT_FOUND_EXCEPTION;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mehra9.poetinsta.dto.UserResponse;
import com.mehra9.poetinsta.exception.GeneralException;
import com.mehra9.poetinsta.exception.NotFoundException;
import com.mehra9.poetinsta.models.User;
import com.mehra9.poetinsta.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserResponse getUSer() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!(principal instanceof UserDetails)) {
			throw new GeneralException(USER_INSTANCE_TYPE_EXCEPTION);
		}
			User user;
			String email = ((UserDetails) principal).getUsername();
			user = userService.findByEmail(email);
			if (user == null) {
				throw new NotFoundException(USER_NOT_FOUND_EXCEPTION);
			}
			UserResponse ur = userService.getUserDetails(user);

			return ur;
	}

	@PostMapping("/upload")
	public UserResponse updateUserImage(@RequestParam("imageFile") MultipartFile file) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!(principal instanceof UserDetails)) {
			throw new GeneralException(USER_INSTANCE_TYPE_EXCEPTION);
		}
			User user;
			String email = ((UserDetails) principal).getUsername();
			user = userService.findByEmail(email);
			if (user == null) {
				throw new NotFoundException(USER_NOT_FOUND_EXCEPTION);
			}
			try {
				userService.updateUserImage(user, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
				throw new GeneralException(e.getLocalizedMessage());
			}

			return userService.getUserDetails(user);

	}

	@GetMapping("/image")
	public byte[] getUserImage() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!(principal instanceof UserDetails)) {
			throw new GeneralException(USER_INSTANCE_TYPE_EXCEPTION);
		}
			User user;
			String email = ((UserDetails) principal).getUsername();
			user = userService.findByEmail(email);
			if (user == null) {
				throw new NotFoundException(USER_NOT_FOUND_EXCEPTION);
			}
			return userService.getUserImage(user);

	}

}
