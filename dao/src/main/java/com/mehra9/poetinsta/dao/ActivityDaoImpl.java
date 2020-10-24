package com.mehra9.poetinsta.dao;

import org.springframework.stereotype.Repository;

import com.mehra9.poetinsta.models.Activity;

@Repository("activityDao")
public class ActivityDaoImpl extends GenericDaoImpl<Activity, Long> implements ActivityDao{

	protected ActivityDaoImpl() {
		super(Activity.class);
		// TODO Auto-generated constructor stub
	}

}
