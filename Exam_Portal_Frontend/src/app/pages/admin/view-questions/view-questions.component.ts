import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/services/question-service/question.service';
import Swal from 'sweetalert2';

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

  deleteQuestion(questionId:any){
    console.log(questionId);
    Swal.fire({
      icon: 'info',
      title: 'Are you sure ?',
      confirmButtonText: 'Delete',
      showCancelButton: true,
    }).then((result)=>{
      if(result.isConfirmed){
        this.service.deleteQuestionService(questionId).subscribe((data:any)=>{
          console.log(data);
          location.reload();
        },
        (error:any)=>{
          console.log(error);
        })
      }
    })
  }

}
