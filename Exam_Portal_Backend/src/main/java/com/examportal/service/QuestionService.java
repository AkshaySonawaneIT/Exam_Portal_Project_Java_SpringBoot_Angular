package com.examportal.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.examportal.POJO.exam.Question;

public interface QuestionService {

	public ResponseEntity<String> addQuestion(Map<String, String> requestMap);
	public ResponseEntity<String> updateQuestion(Map<String, String> requestMap);
	public ResponseEntity<List<Question>> getAllQuestion();
	public ResponseEntity<List<Question>> getQuestionById(Long id);
	public ResponseEntity<String> deleteQuestion(Long id);
	public ResponseEntity<List<Question>> getQuizQuestions(Long id);
}
