package com.examportal.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.examportal.POJO.exam.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Long>{
	List<Quiz> getAllQuiz();
	List<Quiz> getAllQuizAdmin();
	List<Quiz> getQuizId(Long id);
	
	@Modifying
	@Transactional
	void updateQuizStatus(Long id);
}
