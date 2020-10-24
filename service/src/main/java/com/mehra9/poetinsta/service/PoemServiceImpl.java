package com.mehra9.poetinsta.service;

import static com.mehra9.poetinsta.utils.ProjectConstants.COMMENT_NOT_FOUND_EXCEPTION;
import static com.mehra9.poetinsta.utils.ProjectConstants.POEM_NOT_FOUND_EXCEPTION;
import static com.mehra9.poetinsta.utils.ProjectConstants.POEM_NOT_FOUND_USER_EXCEPTION;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mehra9.poetinsta.dto.CommentResponse;
import com.mehra9.poetinsta.dto.PoemResponse;
import com.mehra9.poetinsta.dao.CommentDao;
import com.mehra9.poetinsta.dao.PoemDao;
import com.mehra9.poetinsta.exception.NotFoundException;
import com.mehra9.poetinsta.models.Comment;
import com.mehra9.poetinsta.models.Poem;
import com.mehra9.poetinsta.models.User;


@Service
@Transactional
public class PoemServiceImpl implements PoemService {

	@Autowired
	private PoemDao poemDao;

	@Autowired
	private CommentDao commentDao;

	@Override
	public List<PoemResponse> getPoems() {
		List<Poem> poems = poemDao.findAll();
		if (poems == null) {

			throw new NotFoundException(POEM_NOT_FOUND_EXCEPTION);

		}

		List<PoemResponse> poemResponses = new ArrayList<PoemResponse>();
		poems.stream().forEach(poem -> {
			PoemResponse poemResponse = new PoemResponse();
			poemResponse.setId(poem.getId());
			poemResponse.setRating(poem.getRatings());
			poemResponse.setTitle(poem.getTitle());
			poemResponse.setAuthor(poem.getUser().getEmail());
			poemResponse.setContent(poem.getContent());
			poemResponse.setPicByte(poem.getPicByte());
			poemResponses.add(poemResponse);
		});
		return poemResponses;
	}

	public PoemResponse createPoem(Poem poem) {

		Poem addedPoem = poemDao.save(poem);

		PoemResponse poemResponse = new PoemResponse();

		poemResponse.setId(addedPoem.getId());
		poemResponse.setRating(addedPoem.getRatings());
		poemResponse.setTitle(addedPoem.getTitle());
		poemResponse.setAuthor(addedPoem.getUser().getEmail());
		poemResponse.setContent(addedPoem.getContent());
		poemResponse.setPicByte(addedPoem.getPicByte());

		return poemResponse;

	}

	@Override
	public List<PoemResponse> getPoemByUser(User user) {
		List<Poem> poems = poemDao.findByUser(user);
		if (poems == null) {

			throw new NotFoundException(POEM_NOT_FOUND_USER_EXCEPTION);

		}
		List<PoemResponse> poemResponses = new ArrayList<PoemResponse>();
		poems.stream().forEach(poem -> {
			PoemResponse poemResponse = new PoemResponse();
			poemResponse.setId(poem.getId());
			poemResponse.setRating(poem.getRatings());
			poemResponse.setTitle(poem.getTitle());
			poemResponse.setAuthor(poem.getUser().getEmail());
			poemResponse.setContent(poem.getContent());
			poemResponse.setPicByte(poem.getPicByte());
			poemResponses.add(poemResponse);
		});
		return poemResponses;
	}

	@Override
	public PoemResponse getPoemById(Long id) {
		// TODO Auto-generated method stub
		Poem poem = poemDao.findById(id);
		System.out.println("**** Poem from db " + poem);
		if (poem == null) {

			throw new NotFoundException("Poem with given id-" + id + " does not exist");

		}

		PoemResponse poemResponse = new PoemResponse();

		poemResponse.setId(poem.getId());
		poemResponse.setRating(poem.getRatings());
		poemResponse.setTitle(poem.getTitle());
		poemResponse.setAuthor(poem.getUser().getEmail());
		poemResponse.setContent(poem.getContent());
		poemResponse.setPicByte(poem.getPicByte());
		List<CommentResponse> commentResponses = new ArrayList<CommentResponse>();
		List<Comment> comments = poem.getComments();
		comments.stream().forEach(c -> {
			CommentResponse cr = new CommentResponse();
			cr.setContent(c.getContent());
			cr.setEmail(c.getUser().getEmail());
			cr.setId(c.getId());
			commentResponses.add(cr);
		});
		poemResponse.setComments(commentResponses);

		return poemResponse;

	}

	@Override
	public Poem getPoemModelById(Long id) {
		// TODO Auto-generated method stub
		Poem poem = poemDao.findById(id);
		if (poem == null) {

			throw new NotFoundException("Poem with given id-" + id + " does not exist");

		}

		return poem;

	}

	@Override
	public CommentResponse addComment(Comment comment) {
		Comment addedComment = commentDao.save(comment);

		CommentResponse commentResponse = new CommentResponse();
		commentResponse.setContent(addedComment.getContent());
		commentResponse.setEmail(addedComment.getUser().getEmail());
		commentResponse.setId(addedComment.getId());
		commentResponse.setUserImage(addedComment.getUser().getPicByte());

		return commentResponse;
	}

	@Override
	public List<CommentResponse> getCommentsByPoem(Long poemId) {
		Poem poem = new Poem();
		poem.setId(poemId);
		List<Comment> comments = commentDao.findByPoem(poem);
		if (comments == null) {
			throw new NotFoundException(COMMENT_NOT_FOUND_EXCEPTION);
		}
		List<CommentResponse> response = new ArrayList<CommentResponse>();
		comments.stream().forEach(c -> {
			CommentResponse cr = new CommentResponse();
			cr.setContent(c.getContent());
			cr.setEmail(c.getUser().getEmail());
			cr.setUserImage(c.getUser().getPicByte());
			cr.setId(c.getId());
			response.add(cr);
		});
		return response;
	}

	@Override
	public PoemResponse updatePoemById(Long poemId, PoemResponse poemResponse) {
		Poem poem = poemDao.findById(poemId);
		if (poem == null) {

			throw new NotFoundException("Poem with given id-" + poemId + " does not exist");
		}
		Poem p = poem;
		p.setContent(poemResponse.getContent());
		p.setRatings(poemResponse.getRating());
		poemDao.save(p);

		return poemResponse;
	}

}
