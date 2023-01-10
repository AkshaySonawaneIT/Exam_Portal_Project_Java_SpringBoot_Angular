import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/services/question-service/question.service';

@Component({
  selector: 'app-view-questions',
  templateUrl: './view-questions.component.html',
  styleUrls: ['./view-questions.component.css']
})
export class ViewQuestionsComponent implements OnInit {

  constructor(private route: ActivatedRoute, public service : QuestionService){}

  questions:any = [];
  quizId:any;
  quizTitle:any;
  ngOnInit(): void {
    this.quizId = this.route.snapshot.params['qid'];
    this.quizTitle = this.route.snapshot.params['title']
    this.service.getQuizQuestionService(this.quizId).subscribe((result:any)=>{
      console.log(result);
      this.questions = result;
    },
    (msg:any)=>{
      console.log(msg);
    });
  }



}
