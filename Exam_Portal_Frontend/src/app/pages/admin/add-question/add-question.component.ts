import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/services/question-service/question.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent implements OnInit{
  
  constructor(private route: ActivatedRoute, public service : QuestionService){}
  
  quizId: any;

  questionForm = new FormGroup({
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

    this.questionForm = new FormGroup({
      question : new FormControl("", [Validators.required]),
      image: new FormControl(""),
      option1: new FormControl("", [Validators.required]),
      option2: new FormControl("", [Validators.required]),
      option3: new FormControl(""),
      option4: new FormControl(""),
      answer: new FormControl("", [Validators.required]),
      status: new FormControl("true"),
      quiz_qid: new FormControl(this.quizId),
    });
  }

  addQuestion(){
    console.log(this.questionForm.value);
    this.service.addQuestionService(this.questionForm.value).subscribe((result:any)=>{
      console.log(result);
      Swal.fire(result.message);
      this.onClickReset();
    },
    (msg:any)=>{
      console.log(msg);
      Swal.fire("Something went wrong....");
    })
  }

  onClickReset() {
    this.questionForm.reset();
  }

}