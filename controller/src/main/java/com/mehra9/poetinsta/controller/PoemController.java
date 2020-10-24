package com.mehra9.poetinsta.controller;

import static com.mehra9.poetinsta.utils.ProjectConstants.USER_INSTANCE_TYPE_EXCEPTION;
import static com.mehra9.poetinsta.utils.ProjectConstants.USER_NOT_FOUND_EXCEPTION;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mehra9.poetinsta.dto.CommentResponse;
import com.mehra9.poetinsta.dto.PoemResponse;
import com.mehra9.poetinsta.dao.ActivityDao;
import com.mehra9.poetinsta.exception.GeneralException;
import com.mehra9.poetinsta.exception.NotFoundException;
import com.mehra9.poetinsta.models.Activity;
import com.mehra9.poetinsta.models.Comment;
import com.mehra9.poetinsta.models.Poem;
import com.mehra9.poetinsta.models.User;
import com.mehra9.poetinsta.service.PoemService;
import com.mehra9.poetinsta.service.UserService;

@RestController
@RequestMapping("/poems")
@Transactional
public class PoemController {
	@Autowired
	PoemService poemService;

	@Autowired
	UserService userService;

	@Autowired
	ActivityDao activityDao;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PoemResponse> getPoems() {
		List<PoemResponse> response = poemService.getPoems();
		return response;

	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public PoemResponse postPoems(@Valid @RequestParam("poem") String poem,
			@RequestParam("imageFile") Optional<MultipartFile> files) throws IOException {

		Poem poemJson = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			poemJson = objectMapper.readValue(poem, Poem.class);
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
			throw new GeneralException(e1.getLocalizedMessage());
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
			throw new GeneralException(e1.getLocalizedMessage());
		}

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
		poemJson.setUser(user);

		if (files.isPresent())
			poemJson.setPicByte(files.get().getBytes());

		Activity activity = new Activity();
		activity.setDescription("You created a new poem " + poemJson.getTitle());
		activity.setUser(user);
		activityDao.save(activity);

		PoemResponse response = poemService.createPoem(poemJson);
		return response;

	}

	@GetMapping(path = "/{poemId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PoemResponse getPoemById(@PathVariable(value = "poemId") Long poemId) {

		PoemResponse response = poemService.getPoemById(poemId);

		return response;

	}

	@PutMapping(path = "/{poemId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PoemResponse updatePoemById(@Valid @PathVariable(value = "poemId") Long poemId,
			@RequestBody PoemResponse poemResponse) {
		PoemResponse response = poemService.updatePoemById(poemId, poemResponse);
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

		Activity activity = new Activity();
		activity.setDescription("You updated the poem " + response.getTitle());
		activity.setUser(user);
		activityDao.save(activity);

		return response;

	}

	@GetMapping(path = "/poem", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PoemResponse> getPoemsByUser() {
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
		List<PoemResponse> response = poemService.getPoemByUser(user);
		return response;

	}

	@PostMapping(path = "/{poemid}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
	public CommentResponse postComment(@Valid @RequestBody Comment comment, @PathVariable("poemid") Long id) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!(principal instanceof UserDetails)) {
			throw new GeneralException(USER_INSTANCE_TYPE_EXCEPTION);
		}
		User user;
		String email = ((UserDetails) principal).getUsername();
		user = userService.findByEmail(email);
		Poem poem = poemService.getPoemModelById(id);
		comment.setPoem(poem);
		comment.setUser(user);
		CommentResponse response = poemService.addComment(comment);
		Activity activity = new Activity();
		activity.setDescription("You commented on the poem " + poem.getTitle());
		activity.setUser(user);
		activityDao.save(activity);
		return response;

	}

	@GetMapping(path = "/{poemId}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CommentResponse> getCommentsByPoem(@PathVariable(value = "poemId") Long poemId) {
		List<CommentResponse> response = poemService.getCommentsByPoem(poemId);
		return response;

	}

}
