import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryServiceService } from 'src/app/services/category-services/category-service.service';
import { QuizService } from 'src/app/services/quiz-service/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-quiz',
  templateUrl: './update-quiz.component.html',
  styleUrls: ['./update-quiz.component.css']
})
export class UpdateQuizComponent implements OnInit {


  constructor(private route: ActivatedRoute, private service: QuizService, public categoryService: CategoryServiceService, private router: Router) { }

  quizId = 0;
  quiz: any = [{}];
  categories: any = [{}];

  quizForm = new FormGroup({
    qid: new FormControl(""),
    category_cid: new FormControl(""),
    title: new FormControl("", [Validators.required]),
    description: new FormControl("", [Validators.required]),
    noofquestions: new FormControl("", [Validators.required]),
    maxmarks: new FormControl("", [Validators.required]),
    active: new FormControl("false")
  });

  ngOnInit(): void {
    this.quizId = this.route.snapshot.params['qid'];
    this.service.getQuizByIdService(this.quizId).subscribe((result: any) => {
      this.quiz = result;
      console.log(this.quiz[0]);
      this.quizForm = new FormGroup({
        qid: new FormControl(this.quiz[0].qid),
        category_cid: new FormControl(this.quiz[0].category.cid),
        title: new FormControl(this.quiz[0].title, [Validators.required]),
        description: new FormControl(this.quiz[0].description, [Validators.required]),
        noofquestions: new FormControl(this.quiz[0].noofquestions, [Validators.required]),
        maxmarks: new FormControl(this.quiz[0].maxmarks, [Validators.required]),
        active: new FormControl(this.quiz[0].isActive)
      });
  
    },
    (error: any) => {
        console.log(error);
    });

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



  updateQuiz() {
    console.log(this.quizForm.value)
    this.service.updateQuizService(this.quizForm.value).subscribe((result:any)=>{
      console.log(result.message);
      Swal.fire(result.message);
      this.router.navigate(['admin-dashboard/quizzes']);
    },
    (msg:any)=>{
      console.log(msg);
      Swal.fire("Something Went Wrong....");
    })
  }

}
