package com.mehra9.poetinsta.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {

	@Autowired
	EntityManager em;

	protected final Class<T> entityClass;

	protected GenericDaoImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}


	public T findById(ID id) {
		return em.find(entityClass, id);
	}
	
	 public List<T> findAll() {
	        CriteriaQuery<T> c =
	            em.getCriteriaBuilder().createQuery(entityClass);
	        c.select(c.from(entityClass));
	        return em.createQuery(c).getResultList();
	    }
	 
	    public Long getCount() {
	        CriteriaQuery<Long> c =
	           em.getCriteriaBuilder().createQuery(Long.class);
	        c.select(em.getCriteriaBuilder().count(c.from(entityClass)));
	        return em.createQuery(c).getSingleResult();
	    }
	    
	   
	    public T save(final T entity) {
	    	return em.merge(entity);
	    }


	    public T update(final T entity) {
	        em.merge(entity);
	        em.flush();
	        return entity;
	    }


	    public void delete(final ID uuid) {
	    	em.remove(findById(uuid));
	    	em.flush();
	    }
	
	

}
