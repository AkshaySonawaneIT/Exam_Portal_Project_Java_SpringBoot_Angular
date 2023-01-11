import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from '../helper';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  constructor(private http : HttpClient) { }

  // Get Questions as per quiz
  public getQuizQuestionService(quizId:any){
    return this.http.get(`${baseUrl}/question/getQuizQuestions/${quizId}`, quizId);
  }

  // Add a question
  public addQuestionService(question:any){
    return this.http.post(`${baseUrl}/question/addQuestion`, question);
  }

  // Delete question
  public deleteQuestionService(questionId:any){
    return this.http.post(`${baseUrl}/question/deleteQuestion/${questionId}`, questionId);
  }

  // Get Question by Id
  public getQuestionByIdService(questId:any){
    return this.http.get(`${baseUrl}/question/getQuestion/${questId}`, questId);
  }

  // Update Question 
  public updateQuestionService(question:any){
    return this.http.post(`${baseUrl}/question/updateQuestion`, question);
  }
}
