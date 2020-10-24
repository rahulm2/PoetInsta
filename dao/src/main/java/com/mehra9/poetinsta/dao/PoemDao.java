package com.mehra9.poetinsta.dao;

import java.util.List;

import com.mehra9.poetinsta.models.Poem;
import com.mehra9.poetinsta.models.User;

public interface PoemDao extends GenericDao<Poem, Long> {
	
	List<Poem> findByUser(User user);

}
