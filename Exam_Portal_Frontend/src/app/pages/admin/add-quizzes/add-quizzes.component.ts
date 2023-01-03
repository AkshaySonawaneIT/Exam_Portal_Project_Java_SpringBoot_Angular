import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CategoryServiceService } from 'src/app/services/category-services/category-service.service';
import { QuizService } from 'src/app/services/quiz-service/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-quizzes',
  templateUrl: './add-quizzes.component.html',
  styleUrls: ['./add-quizzes.component.css']
})
export class AddQuizzesComponent implements OnInit{

  constructor(private service: QuizService, private snack: MatSnackBar, public categoryService: CategoryServiceService) { }

  categories: any = [{}];

  ngOnInit(): void {
    this.categoryService.getCategory().subscribe((data: any) => {
      console.log(data);
      //console.log(data[0].cid);
      this.categories = data;
    },
      (msg: any) => {
        console.log(msg);
        Swal.fire("Error in loading categories");
      })
   }


  quizForm = new FormGroup({
    category_cid : new FormControl(""),
    title: new FormControl("", [Validators.required]),
    description: new FormControl("", [Validators.required]),
    noofquestions: new FormControl("", [Validators.required]),
    maxmarks: new FormControl("", [Validators.required]),
    active: new FormControl("false")
  });


  addQuiz() {
    console.log("Add Category")
    console.log(this.quizForm.value);
    this.service.addQuizService(this.quizForm.value).subscribe((data:any)=>{
      console.log("Add Category Success");
      console.log(data.message)
      Swal.fire(data.message);
    },
    (msg:any)=>{
      console.log("Add Category Error");
      console.log(msg);
      if(msg.error.message=="Category is already exists"){
        Swal.fire(msg.error.message)
      }
      else{
        Swal.fire("Server Error");
      }
    })
  }


  onClickReset() {
    this.quizForm.reset();
  }
}
