package com.examportal.POJO.exam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

@NamedQuery(name = "Question.getAllQuestion", query = "select q from Question q where q.status='true'")
@NamedQuery(name = "Question.getQuestionById", query = "select q from Question q where q.id=:id and q.status='true'")
@NamedQuery(name = "Question.updateQuestionStatus", query = "update Question q set q.status='false' where q.qestid=:id")
@Entity
@Table(name = "question")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long qestid;

	@Column(length = 5000)
	private String question;

	private String image;

	private String option1;

	private String option2;

	private String option3;

	private String option4;

	private String answer;

	private String status;

	@ManyToOne(fetch = FetchType.EAGER)
	private Quiz quiz;

	public Question() {

	}

	public Long getQestid() {
		return qestid;
	}

	public void setQestid(Long qestid) {
		this.qestid = qestid;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
