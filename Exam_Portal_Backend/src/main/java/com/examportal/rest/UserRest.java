package com.examportal.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.examportal.POJO.User;


@RequestMapping(path = "/user")
@CrossOrigin("*")
public interface UserRest {

	@PostMapping(path = "/register")
	public ResponseEntity<String> register(@RequestBody(required = true) Map<String, String> requestMap);

	@GetMapping(path = "/getAllUser")
	public ResponseEntity<List<User>> getAllUser();

	@GetMapping(path = "/getUserByEmail/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable String email);
	
	@PostMapping(path = "/delete/{email}")
	public ResponseEntity<String> deleteUserByEmail(@PathVariable String email);
}
