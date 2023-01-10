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

  // Get Quiz By Id
  public getQuizByIdService(quizId:any){
    return this.http.get(`${baseUrl}/quiz/getQuiz/${quizId}`, quizId);
  }

  // Update Quiz
  public updateQuizService(quiz:any){
    return this.http.post(`${baseUrl}/quiz/updateQuiz`, quiz);
  }
}
