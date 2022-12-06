package com.examportal.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.POJO.User;
import com.examportal.constants.ExamPortalConstants;
import com.examportal.rest.UserRest;
import com.examportal.service.UserService;
import com.examportal.utils.ExamPortalUtils;

@RestController
public class UserRestImpl implements UserRest{

	@Autowired
	UserService userService;
	
	@Override
	public ResponseEntity<String> register(Map<String, String> requestMap) {
		try {
			return userService.register(requestMap);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ExamPortalUtils.getResponseEntity(ExamPortalConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<User>> getAllUser() {
		try {
			return userService.getAllUser();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<User>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR); 
	}

	@Override
	public ResponseEntity<User> getUserByEmail(String email) {
		System.out.println("Inside getUserByEmail RestImpl...");
		try {
			return userService.getUserByEmail(email);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseEntity<String> deleteUserByEmail(String email) {
		try {
			return userService.deleteUserByEmail(email);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ExamPortalUtils.getResponseEntity(ExamPortalConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> login(Map<String, String> requestMap) {
		try {
			return userService.login(requestMap);
		}
		catch(Exception e) {
			
		}
		return ExamPortalUtils.getResponseEntity(ExamPortalConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
