import { Component, OnInit } from '@angular/core';
import { QuizService } from 'src/app/services/quiz-service/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-quizzes',
  templateUrl: './view-quizzes.component.html',
  styleUrls: ['./view-quizzes.component.css']
})
export class ViewQuizzesComponent implements OnInit {

  constructor(public service: QuizService) { }

  quiz: any = []

  ngOnInit(): void {
    this.service.getQuizService().subscribe((data: any) => {
      console.log("Quiz Get Data Success : ");
      console.log(data);
      this.quiz = data;
    },
      (msg) => {
        console.log("Quiz get Error : ");
        console.log(msg);
        Swal.fire("Error in loading quizzes");
      })
  }

  deleteQuiz(q:any){

    Swal.fire({
      icon: 'info',
      title: 'Are you sure ?',
      confirmButtonText: 'Delete',
      showCancelButton: true,
    }).then((result)=>{
      if(result.isConfirmed){
        console.log(q)
        this.service.deleteQuizService(q).subscribe((data:any)=>{
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
