package com.mehra9.poetinsta.dao;

import java.util.List;

public interface GenericDao<T, ID> {

	T findById(ID id);

	List<T> findAll();

	Long getCount();

	T save(T t);

	void delete(ID id);

	T update(T t);
}
