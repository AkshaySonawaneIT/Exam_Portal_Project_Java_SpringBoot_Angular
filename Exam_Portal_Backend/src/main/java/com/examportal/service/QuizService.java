package com.examportal.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.examportal.POJO.exam.Quiz;

public interface QuizService {

	public ResponseEntity<String> addQuiz(Map<String, String> requestMap);
	public ResponseEntity<String> updateQuiz(Map<String, String> requestMap);
	public ResponseEntity<List<Quiz>> getAllQuiz();
	public ResponseEntity<List<Quiz>> getQuizById(Long id);
	public ResponseEntity<String> deleteQuiz(Long id);
}
