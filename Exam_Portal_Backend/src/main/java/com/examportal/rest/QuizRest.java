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

import com.examportal.POJO.exam.Quiz;

@RequestMapping(path = "/quiz")
@CrossOrigin("*")
public interface QuizRest {
	
	@PostMapping(path = "/addQuiz")
	public ResponseEntity<String> addQuiz(@RequestBody(required = true) Map<String, String> requestMap);
	
	@PostMapping(path = "/updateQuiz")
	public ResponseEntity<String> updateQuiz(@RequestBody(required = true) Map<String, String> requestMap);
	
	@GetMapping(path = "/getAllQuiz")
	public ResponseEntity<List<Quiz>> getAllQuiz();

	@GetMapping(path = "/getQuiz/{id}")
	public ResponseEntity<List<Quiz>> getQuizById(@PathVariable Long id);

	@PostMapping(path = "/deleteQuiz/{id}")
	public ResponseEntity<String> deleteQuiz(@PathVariable Long id);
}
