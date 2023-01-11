import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/services/question-service/question.service';
import { QuizService } from 'src/app/services/quiz-service/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-question',
  templateUrl: './update-question.component.html',
  styleUrls: ['./update-question.component.css']
})
export class UpdateQuestionComponent implements OnInit{
  
  constructor(private route: ActivatedRoute, public service : QuestionService, public quizService : QuizService){}

  quizId : any;
  questId : any;
  question:any;
  quiz : any;
  questionForm = new FormGroup({
    qestid: new FormControl(""),
    question : new FormControl("", [Validators.required]),
    image: new FormControl(""),
    option1: new FormControl("", [Validators.required]),
    option2: new FormControl("", [Validators.required]),
    option3: new FormControl(""),
    option4: new FormControl(""),
    answer: new FormControl("", [Validators.required]),
    status: new FormControl("true"),
    quiz_qid: new FormControl(""),
  });
  ngOnInit(): void {
    this.quizId = this.route.snapshot.params['qid'];
    this.questId = this.route.snapshot.params['questid'];
    
    this.quizService.getQuizService().subscribe((result:any)=>{
      console.log(result);
      this.quiz = result;
    },
    (error:any)=>{
      console.log(error);
    })

    this.service.getQuestionByIdService(this.questId).subscribe((result:any)=>{
      console.log(result[0]);
      this.question = result[0];
      this.questionForm = new FormGroup({
        qestid: new FormControl(this.question.qestid),
        question : new FormControl(this.question.question, [Validators.required]),
        image: new FormControl(""),
        option1: new FormControl(this.question.option1, [Validators.required]),
        option2: new FormControl(this.question.option2, [Validators.required]),
        option3: new FormControl(this.question.option3),
        option4: new FormControl(this.question.option4),
        answer: new FormControl(this.question.answer, [Validators.required]),
        status: new FormControl(this.question.status, [Validators.required]),
        quiz_qid: new FormControl(this.question.quiz.qid, [Validators.required]),
      });
    },
    (error:any)=>{
      console.log(error);
    })    
  }

  updateQuestion(){
    console.log(this.questionForm.value)
    this.service.updateQuestionService(this.questionForm.value).subscribe((result:any)=>{
      console.log(result.message);
      Swal.fire(result.message);
    },
    (error:any)=>{
      console.log(error.message);
      Swal.fire("Something went wrong ....");
    })
  }

  onClickReset() {
    this.questionForm.reset();
  }
}
