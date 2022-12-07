package com.examportal.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.examportal.Dao.UserDao;
import com.examportal.JWT.JwtUtil;
import com.examportal.JWT.StudentUserDetailsService;
import com.examportal.POJO.User;
import com.examportal.constants.ExamPortalConstants;
import com.examportal.emailServices.EmailService;
import com.examportal.service.UserService;
import com.examportal.utils.ExamPortalUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	EmailService emailService;
	
	@Autowired
	StudentUserDetailsService service;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public ResponseEntity<String> register(Map<String, String> requestMap) {
		System.out.println("Inside register UserServiceImpl" + requestMap);
		try {
			if (validateRegisterMap(requestMap) && requestMap.get("email") != null) {
				User user = userDao.findByEmailId(requestMap.get("email"));
				if (Objects.isNull(user)) {
					userDao.save(getUserFromMap(requestMap));
					emailService.registrationEmail(requestMap.get("email"));
					return ExamPortalUtils.getResponseEntity("User registered successfully", HttpStatus.OK);
				} else {
					return ExamPortalUtils.getResponseEntity("Email is already exists", HttpStatus.BAD_REQUEST);
				}
			} else {
				return ExamPortalUtils.getResponseEntity(ExamPortalConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ExamPortalUtils.getResponseEntity(ExamPortalConstants.SOMETHING_WENT_WRONG,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private boolean validateRegisterMap(Map<String, String> requestMap) {
		if (requestMap.containsKey("name") && requestMap.containsKey("contactNumber") && requestMap.containsKey("email")
				&& requestMap.containsKey("password")) {
			return true;
		} else {
			return false;
		}
	}

	private User getUserFromMap(Map<String, String> requMap) {
		User user = new User();
		user.setName(requMap.get("name"));
		user.setContactNumber(requMap.get("contactNumber"));
		user.setEmail(requMap.get("email"));
		user.setPassword(bCryptPasswordEncoder.encode(requMap.get("password")));
		user.setStatus("true");
		user.setRole("user");
		user.setProfile("default.png");
		user.setDeleted("false");
		return user;
	}

	@Override
	public ResponseEntity<List<User>> getAllUser() {
		System.out.println("Inside getAllUser");
		try {
			return new ResponseEntity<>(userDao.getAllUser(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<User>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<User> getUserByEmail(String email) {
		System.out.println("Inside getUserByEmail");
		try {
			User userObj = userDao.findByEmail(email);
			if (!Objects.isNull(userObj)) {
				return new ResponseEntity<User>(userObj, HttpStatus.OK);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseEntity<String> deleteUserByEmail(String email) {
		System.out.println("Inside deleteUserByEmail");
		try {
			User userObj = userDao.findByEmail(email);
			if (!Objects.isNull(userObj)) {
				userDao.deleteById(userObj.getId());
				return ExamPortalUtils.getResponseEntity("User Deleted Successfully", HttpStatus.OK);
			} else {
				return ExamPortalUtils.getResponseEntity("User not found with this email : " + email, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ExamPortalUtils.getResponseEntity(ExamPortalConstants.SOMETHING_WENT_WRONG,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> login(Map<String, String> requestMap) {
		System.out.println("Inside login method..."+requestMap);
		try {
			Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password")));
			
			if(auth.isAuthenticated()) {
				System.out.println("Inside isAuthenticated...");
				if(service.getUserDetails().getStatus().equalsIgnoreCase("true")) {
					System.out.println("Inside if login...");
					String token = jwtUtil.generateToken(service.getUserDetails().getEmail(), service.getUserDetails().getRole());
					System.out.println("Token : "+token);
					return new ResponseEntity<String>(token, HttpStatus.OK);
				}
				else {
					System.out.println("Inside else login...");
					return new ResponseEntity<String>("{\"message\":\""+"Wait for admin approval."+"\"}", HttpStatus.BAD_REQUEST);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		String msg = "Bad Credentials";
		return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
	}

}
