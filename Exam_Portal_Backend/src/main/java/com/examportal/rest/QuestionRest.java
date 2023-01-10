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

import com.examportal.POJO.exam.Question;

@RequestMapping(path = "question")
@CrossOrigin
public interface QuestionRest {

	@PostMapping(path = "/addQuestion")
	public ResponseEntity<String> addQuestion(@RequestBody(required = true) Map<String, String> requestMap);
	
	@PostMapping(path = "/updateQuestion")
	public ResponseEntity<String> updateQuestion(@RequestBody(required = true) Map<String, String> requestMap);
	
	@GetMapping(path = "/getAllQuestion")
	public ResponseEntity<List<Question>> getAllQuestion();

	@GetMapping(path = "/getQuestion/{id}")
	public ResponseEntity<List<Question>> getQuestionById(@PathVariable Long id);

	@PostMapping(path = "/deleteQuestion/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable Long id);
	
	@GetMapping(path = "/getQuizQuestions/{id}")
	public ResponseEntity<List<Question>> getQuizQuestions(@PathVariable Long id);
}
