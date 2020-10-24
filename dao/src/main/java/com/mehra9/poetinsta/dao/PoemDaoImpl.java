package com.mehra9.poetinsta.dao;


import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.mehra9.poetinsta.models.Poem;
import com.mehra9.poetinsta.models.User;

@Repository("poemDao")
public class PoemDaoImpl extends GenericDaoImpl<Poem, Long> implements PoemDao {

	protected PoemDaoImpl() {
		super(Poem.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Poem> findByUser(User user) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Poem> q = cb.createQuery(Poem.class);
		Root<Poem> c = q.from(Poem.class);
		q.select(c).where(cb.equal(c.get("user"), user));

		return em.createQuery(q).getResultList();
	}

}
