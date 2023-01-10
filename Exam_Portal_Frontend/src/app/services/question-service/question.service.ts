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
}
