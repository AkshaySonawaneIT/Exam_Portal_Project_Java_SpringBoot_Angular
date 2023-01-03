import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from '../helper';

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  constructor(private http : HttpClient) { }

  // Get All Quiz
  public getQuizService(){
    return this.http.get(`${baseUrl}/quiz/getAllQuiz`);
  }

  // Add Quiz
  public addQuizService(quiz:any){
    return this.http.post(`${baseUrl}/quiz/addQuiz`, quiz);
  }

  // Delete Quiz
  public deleteQuizService(quiz:any){
    console.log(quiz)
    return this.http.post(`${baseUrl}/quiz/deleteQuiz/${quiz.qid}`, quiz.qid);
  }
}
