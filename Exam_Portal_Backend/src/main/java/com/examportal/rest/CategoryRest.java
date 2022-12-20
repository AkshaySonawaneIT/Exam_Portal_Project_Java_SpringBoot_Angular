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

import com.examportal.POJO.exam.Category;

@RequestMapping(path = "/category")
@CrossOrigin("*")
public interface CategoryRest {

	@PostMapping(path = "/addCategory")
	public ResponseEntity<String> addCategory(@RequestBody(required = true) Map<String, String> requestMap);
	
	@PostMapping(path = "/updateCategory")
	public ResponseEntity<String> updateCategory(@RequestBody(required = true) Map<String, String> requestMap);
	
	@GetMapping(path = "/getAllCategory")
	public ResponseEntity<List<Category>> getAllCategory();
	
	@GetMapping(path = "/getCategory/{id}")
	public ResponseEntity<List<Category>> getCategoryById(@PathVariable Long id);

	@PostMapping(path = "/deleteCategory/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long id);
}
