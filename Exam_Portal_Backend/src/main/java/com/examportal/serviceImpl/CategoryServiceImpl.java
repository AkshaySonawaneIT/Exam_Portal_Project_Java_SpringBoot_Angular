package com.examportal.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.examportal.Dao.CategoryDao;
import com.examportal.JWT.JwtFilter;
import com.examportal.POJO.exam.Category;
import com.examportal.constants.ExamPortalConstants;
import com.examportal.service.CategoryService;
import com.examportal.utils.ExamPortalUtils;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;

	@Autowired
	JwtFilter jwtFilter;

	@Override
	public ResponseEntity<String> addCategory(Map<String, String> requestMap) {
		try {
			if (jwtFilter.isAdmin()) {
				if (validateCategoryMap(requestMap)) {
					Category category = categoryDao.findByTitle(requestMap.get("title"));
					if (Objects.isNull(category)) {
						categoryDao.save(getCategoryFromMap(requestMap, false));
						return ExamPortalUtils.getResponseEntity("Category added successfully", HttpStatus.OK);
					} else {
						return ExamPortalUtils.getResponseEntity("Category is already exists", HttpStatus.BAD_REQUEST);
					}
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

	private boolean validateCategoryMap(Map<String, String> requestMap) {
		if (requestMap.containsKey("title") && requestMap.containsKey("description")) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean validateCategoryMap1(Map<String, String> requestMap) {
		if (requestMap.containsKey("id") && requestMap.containsKey("title") && requestMap.containsKey("description")) {
			return true;
		} else {
			return false;
		}
	}

	private Category getCategoryFromMap(Map<String, String> requMap, boolean isAdd) {
		Category category = new Category();
		category.setTitle(requMap.get("title"));
		category.setDescription(requMap.get("description"));
		if(isAdd) {
			category.setCid((long) Integer.parseInt(requMap.get("id")));
		}
		return category;
	}

	@Override
	public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
		try {
			if(jwtFilter.isAdmin()) {
				if(validateCategoryMap1(requestMap)) {
					Optional<Category> optional = categoryDao.findById((long) Integer.parseInt(requestMap.get("id")));
					if(!optional.isEmpty()) {
						Category category = getCategoryFromMap(requestMap, true);
						categoryDao.save(category);
						return ExamPortalUtils.getResponseEntity("Category updated successfully...", HttpStatus.OK);
					}
					else {
						return ExamPortalUtils.getResponseEntity("Category id not found...", HttpStatus.OK);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ExamPortalUtils.getResponseEntity(ExamPortalConstants.SOMETHING_WENT_WRONG,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Category>> getAllCategory() {
		try {
			return new ResponseEntity<>(categoryDao.getAllCategory(), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Category>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Category>> getCategoryById(Long id) {
		try {
			return new ResponseEntity<>(categoryDao.getCategoryId(id), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Category>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> deleteCategory(Long id) {
		try {
			if(jwtFilter.isAdmin()) {
				Optional optional = categoryDao.findById(id);
				if(!optional.isEmpty()) {
					categoryDao.deleteById(id);
					return ExamPortalUtils.getResponseEntity("Category deleted successfully...", HttpStatus.OK);
				}
				else {
					return ExamPortalUtils.getResponseEntity("Category id not found...", HttpStatus.OK);
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

}
