package com.mehra9.poetinsta.dao;

import com.mehra9.poetinsta.models.User;

public interface UserDao extends GenericDao<User, Long>{

	User findByEmail(String email);

}
