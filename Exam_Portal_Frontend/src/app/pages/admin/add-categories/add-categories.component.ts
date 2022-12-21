import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CategoryServiceService } from 'src/app/services/category-services/category-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-categories',
  templateUrl: './add-categories.component.html',
  styleUrls: ['./add-categories.component.css']
})
export class AddCategoriesComponent implements OnInit {

  constructor(private service: CategoryServiceService, private snack: MatSnackBar) { }

  ngOnInit(): void { }


  categoryForm = new FormGroup({
    title: new FormControl("", [Validators.required]),
    description: new FormControl("", [Validators.required]),
  });


  addCategory() {
    console.log("Add Category")
    console.log(this.categoryForm.value);
    this.service.addCategoryService(this.categoryForm.value).subscribe((data:any)=>{
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
    this.categoryForm.reset();
  }
}
