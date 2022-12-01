package com.examportal.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.examportal.POJO.User;

public interface UserService {

	ResponseEntity<String> register(Map<String, String> requestMap);
	ResponseEntity<List<User>> getAllUser();
	ResponseEntity<User> getUserByEmail(String email);
	ResponseEntity<String> deleteUserByEmail(String email);
}
