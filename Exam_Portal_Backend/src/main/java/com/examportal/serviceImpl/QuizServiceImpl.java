package com.examportal.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.examportal.Dao.QuizDao;
import com.examportal.JWT.JwtFilter;
import com.examportal.POJO.exam.Category;
import com.examportal.POJO.exam.Quiz;
import com.examportal.constants.ExamPortalConstants;
import com.examportal.service.QuizService;
import com.examportal.utils.ExamPortalUtils;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	QuizDao quizDao;

	@Autowired
	JwtFilter jwtFilter;

	@Override
	public ResponseEntity<String> addQuiz(Map<String, String> requestMap) {
		try {
			if (jwtFilter.isAdmin()) {
				if(validateQuizMap(requestMap, false)) {
					quizDao.save(getQuizFromMap(requestMap, false));
					return ExamPortalUtils.getResponseEntity("Quiz added successfully", HttpStatus.OK);
				}
				else {
					return ExamPortalUtils.getResponseEntity(ExamPortalConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
				}
			} else {
				return ExamPortalUtils.getResponseEntity(ExamPortalConstants.UNAUTHORIZED_ACCESS,
						HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ExamPortalUtils.getResponseEntity(ExamPortalConstants.SOMETHING_WENT_WRONG,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private boolean validateQuizMap(Map<String, String> requestMap, boolean validateId) {
		System.out.println("Inside validateQuizMap");
		if (requestMap.containsKey("title") && requestMap.containsKey("description")
				&& requestMap.containsKey("maxmarks") && requestMap.containsKey("noofquestions")
				&& requestMap.containsKey("category_cid")) {
			if (requestMap.containsKey("qid") && validateId) {
				return true;
			} else if (!validateId) {
				return true;
			}
		}
		return false;
	}

	private Quiz getQuizFromMap(Map<String, String> requestMap, boolean isAdd) {
		System.out.println("Inside getQuizFromMap");
		Quiz quiz = new Quiz();
		Category category = new Category();
		category.setCid((long)Integer.parseInt(requestMap.get("category_cid")));
		if(isAdd) {
			quiz.setQid((long)Integer.parseInt(requestMap.get("qid")));
		}
		quiz.setTitle(requestMap.get("title"));
		quiz.setDescription(requestMap.get("description"));
		quiz.setNoofquestions(requestMap.get("noofquestions"));
		quiz.setMaxmarks(requestMap.get("maxmarks"));
		quiz.setActive(requestMap.get("active"));
		quiz.setCategory(category);
		return quiz;
	}

	@Override
	public ResponseEntity<String> updateQuiz(Map<String, String> requestMap) {
		System.out.println("Inside updateQuiz ServiceImpl");
		try {
			if(jwtFilter.isAdmin()) {
				if(validateQuizMap(requestMap, true)) {
					Optional optional = quizDao.findById((long)Integer.parseInt(requestMap.get("qid")));
					if(!optional.isEmpty()) {
						Quiz quiz = getQuizFromMap(requestMap, true);
						quizDao.save(quiz);
						return ExamPortalUtils.getResponseEntity("Quiz updated successfully...", HttpStatus.OK);
					}
					else {
						return ExamPortalUtils.getResponseEntity("Quiz id not found...", HttpStatus.OK);
					}
				}
				else {
					return ExamPortalUtils.getResponseEntity(ExamPortalConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
				}
			}
			else {
				return ExamPortalUtils.getResponseEntity(ExamPortalConstants.UNAUTHORIZED_ACCESS,
						HttpStatus.UNAUTHORIZED);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ExamPortalUtils.getResponseEntity(ExamPortalConstants.SOMETHING_WENT_WRONG,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Quiz>> getAllQuiz() {
		try {
			if(jwtFilter.isAdmin()) {
				return new ResponseEntity<>(quizDao.getAllQuizAdmin(), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(quizDao.getAllQuiz(), HttpStatus.OK);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Quiz>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Quiz>> getQuizById(Long id) {
		try {
			return new ResponseEntity<>(quizDao.getQuizId(id), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Quiz>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> deleteQuiz(Long id) {
		try {
			if(jwtFilter.isAdmin()) {
				Optional<Quiz> optional = quizDao.findById(id);
				if(!optional.isEmpty()) {
					quizDao.updateQuizStatus(id);// Delete operation problem
//					System.out.println(optional);
					return ExamPortalUtils.getResponseEntity("Quiz deleted successfully....", HttpStatus.OK);
				}
				else {
					return ExamPortalUtils.getResponseEntity("Quiz id not found....", HttpStatus.OK);
				}
			}
			else {
				return ExamPortalUtils.getResponseEntity(ExamPortalConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ExamPortalUtils.getResponseEntity(ExamPortalConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
