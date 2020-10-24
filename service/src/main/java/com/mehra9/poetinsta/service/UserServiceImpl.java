package com.mehra9.poetinsta.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mehra9.poetinsta.dto.ActivityResponse;
import com.mehra9.poetinsta.dto.PoemResponse;
import com.mehra9.poetinsta.dto.UserResponse;
import com.mehra9.poetinsta.dao.UserDao;
import com.mehra9.poetinsta.models.Activity;
import com.mehra9.poetinsta.models.Poem;
import com.mehra9.poetinsta.models.Role;
import com.mehra9.poetinsta.models.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	public User updateUserImage(User user, byte[] fileBytes) {

		user.setPicByte(fileBytes);
		userDao.update(user);
		return user;
	}

	public User save(User registration) {
		User user = new User();
		user.setFirstName(registration.getFirstName());
		user.setLastName(registration.getLastName());
		user.setEmail(registration.getEmail());
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		user.setRoles(Arrays.asList(new Role("ROLE_USER")));
		return userDao.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public UserResponse getUserDetails(User user) {
		UserResponse ur = new UserResponse();
		ur.setEmail(user.getEmail());
		ur.setFirstName(user.getFirstName());
		ur.setId(user.getId());
		ur.setPicByte(user.getPicByte());
		ur.setLastName(user.getLastName());
		List<Activity> activities = user.getActivity();
		List<ActivityResponse> activityResponses = new ArrayList<ActivityResponse>();
		activities.stream().forEach(a -> {
			ActivityResponse ar = new ActivityResponse();
			ar.setDescription(a.getDescription());
			ar.setId(a.getId());
			activityResponses.add(ar);
		});
		ur.setActivities(activityResponses);
		List<Poem> poems = user.getPoems();
		List<PoemResponse> poemResponses = new ArrayList<PoemResponse>();
		poems.stream().forEach(p -> {
			PoemResponse pr = new PoemResponse();
			pr.setId(p.getId());
			pr.setContent(p.getContent());
			pr.setRating(p.getRatings());
			pr.setTitle(p.getTitle());
			pr.setPicByte(p.getPicByte());
			poemResponses.add(pr);
		});
		ur.setPoems(poemResponses);
		return ur;
	}

	@Override
	public byte[] getUserImage(User user) {
		return user.getPicByte();
	}

}