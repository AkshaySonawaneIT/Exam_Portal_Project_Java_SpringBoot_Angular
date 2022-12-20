package com.examportal.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.POJO.exam.Category;
import com.examportal.constants.ExamPortalConstants;
import com.examportal.rest.CategoryRest;
import com.examportal.service.CategoryService;
import com.examportal.utils.ExamPortalUtils;

@RestController
public class CategoryRestImpl implements CategoryRest {

	@Autowired
	CategoryService service;

	@Override
	public ResponseEntity<String> addCategory(Map<String, String> requestMap) {
		try {
			return service.addCategory(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ExamPortalUtils.getResponseEntity(ExamPortalConstants.SOMETHING_WENT_WRONG,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
		try {
			return service.updateCategory(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ExamPortalUtils.getResponseEntity(ExamPortalConstants.SOMETHING_WENT_WRONG,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Category>> getAllCategory() {
		try {
			return service.getAllCategory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Category>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Category>> getCategoryById(Long id) {
		try {
			return service.getCategoryById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Category>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> deleteCategory(Long id) {
		try {
			return service.deleteCategory(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ExamPortalUtils.getResponseEntity(ExamPortalConstants.SOMETHING_WENT_WRONG,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
