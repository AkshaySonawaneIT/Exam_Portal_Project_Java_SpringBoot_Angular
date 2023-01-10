package com.examportal.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.examportal.POJO.exam.Question;

public interface QuestionDao extends JpaRepository<Question, Long>{
	List<Question> getAllQuestion();
	List<Question> getQuestionById(Long id);
	List<Question> getAllQuizQuestions(Long id);
	List<Question> getAllQuizQuestionsAdmin(Long id);
	
	@Modifying
	@Transactional
	void updateQuestionStatus(Long id);
}
