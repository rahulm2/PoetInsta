package com.mehra9.poetinsta.dao;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.mehra9.poetinsta.models.User;

@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

	protected UserDaoImpl() {
		super(User.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public User findByEmail(String email) {

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<User> q = cb.createQuery(User.class);
		Root<User> c = q.from(User.class);

		q.select(c).where(cb.equal(c.get("email"), email));
		User user = null;
		try {
		user = em.createQuery(q).getSingleResult();
		}
		catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
		
		return user;
	}


}
