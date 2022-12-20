package com.examportal.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.POJO.exam.Question;
import com.examportal.constants.ExamPortalConstants;
import com.examportal.rest.QuestionRest;
import com.examportal.service.QuestionService;
import com.examportal.utils.ExamPortalUtils;

@RestController
public class QuestionRestImpl implements QuestionRest {

	@Autowired
	QuestionService service;

	@Override
	public ResponseEntity<String> addQuestion(Map<String, String> requestMap) {
		try {
			return service.addQuestion(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ExamPortalUtils.getResponseEntity(ExamPortalConstants.SOMETHING_WENT_WRONG,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateQuestion(Map<String, String> requestMap) {
		try {
			return service.updateQuestion(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ExamPortalUtils.getResponseEntity(ExamPortalConstants.SOMETHING_WENT_WRONG,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Question>> getAllQuestion() {
		try {
			return service.getAllQuestion();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Question>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Question>> getQuestionById(Long id) {
		try {
			return service.getQuestionById(id);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Question>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> deleteQuestion(Long id) {
		try {
			return service.deleteQuestion(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ExamPortalUtils.getResponseEntity(ExamPortalConstants.SOMETHING_WENT_WRONG,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
