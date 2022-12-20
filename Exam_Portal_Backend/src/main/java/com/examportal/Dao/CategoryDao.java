package com.examportal.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.POJO.exam.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {

	Category findByTitle(String title);
	List<Category> getAllCategory();
	List<Category> getCategoryId(Long id);
}
