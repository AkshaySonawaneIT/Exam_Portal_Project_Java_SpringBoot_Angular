package com.examportal.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.examportal.Dao.QuestionDao;
import com.examportal.JWT.JwtFilter;
import com.examportal.POJO.exam.Category;
import com.examportal.POJO.exam.Question;
import com.examportal.POJO.exam.Quiz;
import com.examportal.constants.ExamPortalConstants;
import com.examportal.service.QuestionService;
import com.examportal.utils.ExamPortalUtils;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	JwtFilter jwtFilter;

	@Autowired
	QuestionDao questionDao;

	@Override
	public ResponseEntity<String> addQuestion(Map<String, String> requestMap) {
		try {
			if (jwtFilter.isAdmin()) {
				if (validateQuestionMap(requestMap, false)) {
					questionDao.save(getQuestionFromMap(requestMap, false));
					return ExamPortalUtils.getResponseEntity("Question saved successfully...", HttpStatus.OK);
				} else {
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

	private boolean validateQuestionMap(Map<String, String> requestMap, boolean validateId) {
		System.out.println("Inside validateQuestionMap");
		if (requestMap.containsKey("question") && requestMap.containsKey("image") && requestMap.containsKey("option1")
				&& requestMap.containsKey("option2") && requestMap.containsKey("option3")
				&& requestMap.containsKey("option4") && requestMap.containsKey("answer")) {
			if (requestMap.containsKey("qestid") && validateId) {
				return true;
			} else if (!validateId) {
				return true;
			}
		}
		return false;
	}

	private Question getQuestionFromMap(Map<String, String> requestMap, boolean isAdd) {
		System.out.println("Inside getQuestionFromMap");
		Question question = new Question();
		Quiz quiz = new Quiz();
		quiz.setQid((long) Integer.parseInt(requestMap.get("quiz_qid")));
		if (isAdd) {
			question.setQestid((long) Integer.parseInt(requestMap.get("qestid")));
		}
		question.setQuestion(requestMap.get("question"));
		question.setImage(requestMap.get("image"));
		question.setOption1(requestMap.get("option1"));
		question.setOption2(requestMap.get("option2"));
		question.setOption3(requestMap.get("option3"));
		question.setOption4(requestMap.get("option4"));
		question.setAnswer(requestMap.get("answer"));
		question.setStatus("true");
		question.setQuiz(quiz);
		return question;
	}

	@Override
	public ResponseEntity<String> updateQuestion(Map<String, String> requestMap) {
		try {
			if(jwtFilter.isAdmin()) {
				if(validateQuestionMap(requestMap, true)) {
					Optional optional = questionDao.findById((long)Integer.parseInt(requestMap.get("qestid")));
					if(!optional.isEmpty()) {
						questionDao.save(getQuestionFromMap(requestMap, true));
						return ExamPortalUtils.getResponseEntity("Question updated successfully...", HttpStatus.OK);
					}else {
						return ExamPortalUtils.getResponseEntity("Question id not found...", HttpStatus.OK);
					}
				}else {
					return ExamPortalUtils.getResponseEntity(ExamPortalConstants.INVALID_DATA, HttpStatus.OK);
				}
			}else {
				return ExamPortalUtils.getResponseEntity(ExamPortalConstants.UNAUTHORIZED_ACCESS, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ExamPortalUtils.getResponseEntity(ExamPortalConstants.SOMETHING_WENT_WRONG,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Question>> getAllQuestion() {
		try {
			return new ResponseEntity<>(questionDao.getAllQuestion(), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Question>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Question>> getQuestionById(Long id) {
		try {
			return new ResponseEntity<>(questionDao.getQuestionById(id), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Question>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> deleteQuestion(Long id) {
		try {
			if(jwtFilter.isAdmin()) {
				Optional optional = questionDao.findById(id);
				if(!optional.isEmpty()) {
//					questionDao.deleteById(id);
					questionDao.updateQuestionStatus(id);
					return ExamPortalUtils.getResponseEntity("Question deleted successfully....", HttpStatus.OK);
				}
				else {
					return ExamPortalUtils.getResponseEntity("Question id not found ....", HttpStatus.OK);
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

	@Override
	public ResponseEntity<List<Question>> getQuizQuestions(Long id) {
		try {
			if(jwtFilter.isAdmin()) {
				return new ResponseEntity<>(questionDao.getAllQuizQuestionsAdmin(id), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(questionDao.getAllQuizQuestions(id), HttpStatus.OK);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Question>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR); 
	}

}
