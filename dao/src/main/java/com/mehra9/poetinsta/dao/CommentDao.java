package com.mehra9.poetinsta.dao;

import java.util.List;

import com.mehra9.poetinsta.models.Comment;
import com.mehra9.poetinsta.models.Poem;

public interface CommentDao extends GenericDao<Comment, Long>{
	
	
	List<Comment> findByPoem(Poem poem);

}
