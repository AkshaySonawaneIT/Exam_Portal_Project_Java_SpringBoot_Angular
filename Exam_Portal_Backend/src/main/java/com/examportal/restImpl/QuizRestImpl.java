package com.examportal.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.POJO.exam.Category;
import com.examportal.POJO.exam.Quiz;
import com.examportal.constants.ExamPortalConstants;
import com.examportal.rest.QuizRest;
import com.examportal.service.QuizService;
import com.examportal.utils.ExamPortalUtils;

@RestController
public class QuizRestImpl implements QuizRest {

	@Autowired
	QuizService service;

	@Override
	public ResponseEntity<String> addQuiz(Map<String, String> requestMap) {
		try {
			return service.addQuiz(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ExamPortalUtils.getResponseEntity(ExamPortalConstants.SOMETHING_WENT_WRONG,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateQuiz(Map<String, String> requestMap) {
		System.out.println("Inside updateQuiz RestImpl");
		try {
			return service.updateQuiz(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ExamPortalUtils.getResponseEntity(ExamPortalConstants.SOMETHING_WENT_WRONG,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Quiz>> getAllQuiz() {
		System.out.println("Inside Get All Quiz RestImpl");
		try {
			return service.getAllQuiz();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Quiz>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Quiz>> getQuizById(Long id) {
		System.out.println("Inside Get All Quiz RestImpl");
		try {
			return service.getQuizById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Quiz>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> deleteQuiz(Long id) {
		try {
			return service.deleteQuiz(id);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ExamPortalUtils.getResponseEntity(ExamPortalConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
