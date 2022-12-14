package com.examportal.JWT;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.examportal.Dao.UserDao;

@Service
public class StudentUserDetailsService implements UserDetailsService {

	@Autowired
	UserDao userDao;

	private com.examportal.POJO.User userDetail;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		userDetail = userDao.findByEmailId(username);
		System.out.println("User Detail : "+userDetail.getEmail());
		if (!Objects.isNull(userDetail)) {
			return new User(userDetail.getEmail(), userDetail.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found");
		}
	}

	public com.examportal.POJO.User getUserDetails() {
		return userDetail;
	}

}
