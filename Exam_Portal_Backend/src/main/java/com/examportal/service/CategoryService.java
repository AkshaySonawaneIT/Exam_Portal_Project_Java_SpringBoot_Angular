package com.examportal.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.examportal.POJO.exam.Category;

public interface CategoryService {

	public ResponseEntity<String> addCategory(Map<String, String> requestMap);
	public ResponseEntity<String> updateCategory(Map<String, String> requestMap);
	public ResponseEntity<List<Category>> getAllCategory();
	public ResponseEntity<List<Category>> getCategoryById(Long id);
	public ResponseEntity<String> deleteCategory(Long id);
}
