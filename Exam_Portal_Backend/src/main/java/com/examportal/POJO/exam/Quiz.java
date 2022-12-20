package com.examportal.POJO.exam;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

@NamedQuery(name = "Quiz.getAllQuiz", query = "select q from Quiz q where q.active='true'")
@NamedQuery(name = "Quiz.getQuizId", query = "select q from Quiz q where q.active='true' and q.id=:id")
@NamedQuery(name = "Quiz.updateQuizStatus", query = "update Quiz q set q.active='false' where q.id=:id")
@Entity
@Table(name = "quiz")
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long qid;

	private String title;
	
	@Column(length = 5000)
	private String description;

	private String maxmarks;

	private String noofquestions;

	private String active = "false";
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	
	@OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Question> questions = new HashSet<>();

	public Quiz() {

	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getQid() {
		return qid;
	}

	public void setQid(Long qid) {
		this.qid = qid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMaxmarks() {
		return maxmarks;
	}

	public void setMaxmarks(String maxmarks) {
		this.maxmarks = maxmarks;
	}

	public String getNoofquestions() {
		return noofquestions;
	}

	public void setNoofquestions(String noofquestions) {
		this.noofquestions = noofquestions;
	}

	public String isActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}
