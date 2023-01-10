import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryServiceService } from 'src/app/services/category-services/category-service.service';
import { QuizService } from 'src/app/services/quiz-service/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-category',
  templateUrl: './update-category.component.html',
  styleUrls: ['./update-category.component.css']
})
export class UpdateCategoryComponent implements OnInit {
  
  constructor(private route: ActivatedRoute, public categoryService: CategoryServiceService, private router: Router) { }
  

  category_id : any;
  category:any;
  categoryForm = new FormGroup({
    id: new FormControl(""),
    title: new FormControl("", [Validators.required]),
    description: new FormControl("", [Validators.required]),
  });

  ngOnInit(): void {
    this.category_id = this.route.snapshot.params['cid'];
    this.categoryService.getCategoryById(this.category_id).subscribe((result)=>{
      this.category = result;
      console.log(this.category[0]);
      this.categoryForm = new FormGroup({
        id: new FormControl(this.category[0].cid),
        title: new FormControl(this.category[0].title, [Validators.required]),
        description: new FormControl(this.category[0].description, [Validators.required]),
      });
    },
    (msg)=>{
      console.log(msg);
    })
  }

  updateCategory(){
    console.log(this.categoryForm.value);
    this.categoryService.updatecategoryService(this.categoryForm.value).subscribe((result:any)=>{
      console.log(result.message);
      Swal.fire(result.message);
      this.router.navigate(['admin-dashboard/categories']);
    },
    (msg:any)=>{
      console.log(msg);
      Swal.fire("Something Went Wrong....");
    })
  }

  onClickReset(){
    this.categoryForm.reset();
  }

}
