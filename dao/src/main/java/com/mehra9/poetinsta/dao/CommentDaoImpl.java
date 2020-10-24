package com.mehra9.poetinsta.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.mehra9.poetinsta.models.Comment;
import com.mehra9.poetinsta.models.Poem;


@Repository("commentDao")
public class CommentDaoImpl extends GenericDaoImpl<Comment, Long> implements CommentDao{

	protected CommentDaoImpl() {
		super(Comment.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Comment> findByPoem(Poem poem) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Comment> q = cb.createQuery(Comment.class);
		Root<Comment> c = q.from(Comment.class);
		q.select(c).where(cb.equal(c.get("poem"), poem));

		return em.createQuery(q).getResultList();
		
	}

}
